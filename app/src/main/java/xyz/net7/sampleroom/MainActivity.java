package xyz.net7.sampleroom;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import xyz.net7.sampleroom.fragment.AlertDialogFragment;
import xyz.net7.sampleroom.fragment.ListFragment;
import xyz.net7.sampleroom.model.DataItem;
import xyz.net7.sampleroom.model.DataViewModel;

import static xyz.net7.sampleroom.fragment.AlertDialogFragment.ID_LONG;


public class MainActivity extends AppCompatActivity implements ListFragment.OnListFragmentInteractionListener,
        AlertDialogFragment.AlertDialogListener {

    private DataViewModel mDataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_layout, new ListFragment())
                .addToBackStack("list")
                .commit();
    }

    @Override
    public void onListClickItem(DataItem dataItem) {
        Toast.makeText(this, dataItem.getDetails(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onListFragmentDeleteItemById(long idItem) {
        Bundle bundle = new Bundle();
        bundle.putLong(ID_LONG, idItem);

        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        alertDialogFragment.setArguments(bundle);
        alertDialogFragment.show(getSupportFragmentManager(), "Alerta");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, long idItem) {
        mDataViewModel.deleteItemById(idItem);
        Toast.makeText(this, getString(R.string.message_delete), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(this, getString(R.string.message_cancel), Toast.LENGTH_SHORT).show();

    }
}
