package xyz.net7.sampleroom.model;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class DataRepository {
    private DataDAO mDataDao;
    private LiveData<List<DataItem>> mAllData;

    public DataRepository(Application application) {
        DataRoomDbase dataRoombase = DataRoomDbase.getDatabase(application);
        this.mDataDao = dataRoombase.dataDAO();
        this.mAllData = mDataDao.getAllData();
    }

    LiveData<List<DataItem>> getAllData() {
        return mAllData;
    }


    public void insert(DataItem dataItem) {
        new insertAsyncTask(mDataDao).execute(dataItem);
    }
    //You must call this on a non-UI thread or your app will crash
    private static class insertAsyncTask extends AsyncTask<DataItem, Void, Void> {
        private DataDAO mAsyncTaskDao;
        insertAsyncTask(DataDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final DataItem... params) {
            mAsyncTaskDao.insertItem(params[0]);
            return null;
        }
    }

    public void deleteItem(DataItem dataItem) {
        new deleteAsyncTask(mDataDao).execute(dataItem);
    }
    // Debe llamar a esto en un subproceso que no sea UI o su aplicación se bloqueará
    private static class deleteAsyncTask extends AsyncTask<DataItem, Void, Void> {
        private DataDAO mAsyncTaskDao;
        deleteAsyncTask(DataDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final DataItem... params) {
            mAsyncTaskDao.deleteItem(params[0]);
            return null;
        }
    }

    public void deleteItemById(Long idItem) {
        new deleteByIdAsyncTask(mDataDao).execute(idItem);
    }
    // Debes llamar a  un hilo que no sea UI o tu aplicación se bloqueará
    private static class deleteByIdAsyncTask extends AsyncTask<Long, Void, Void> {
        private DataDAO mAsyncTaskDao;
        deleteByIdAsyncTask(DataDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Long... params) {
            mAsyncTaskDao.deleteByItemId(params[0]);
            return null;
        }
    }

}
