package xyz.net7.sampleroom.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import xyz.net7.sampleroom.R;

public class AlertDialogFragment extends DialogFragment {

    AlertDialogListener mListener;
    public static String ID_LONG = "ID_LONG";
    long id_data;

    public interface AlertDialogListener {
        void onDialogPositiveClick(DialogFragment dialog, long idItem);

        void onDialogNegativeClick(DialogFragment dialog);
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Activity activity = getActivity();
        Bundle bundle = getArguments();

        if (bundle != null && activity != null) {
            // obtiene datos del paquete
            id_data = bundle.getLong(ID_LONG);
            // Usa la clase Builder para la construcción conveniente del diálogo
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage(R.string.message_dialog)
                    .setPositiveButton(R.string.message_yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // establece el oyente para el botón yes
                            mListener.onDialogPositiveClick(AlertDialogFragment.this, id_data);
                        }
                    })
                    .setNegativeButton(R.string.message_no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // El usuario canceló el diálogo
                            mListener.onDialogNegativeClick(AlertDialogFragment.this);
                        }
                    });
            // Crear el objeto de diálogo de alerta y devolverlo
            return builder.create();
        }
        /// si no hay paquete - muestra error
        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setNegativeButton(R.string.message_error, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }

    // Invalide el método Fragment.onAttach () para crear instancias del oyente de diálogo
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verifique que la actividad del host implemente la interfaz de devolución de llamada
        try {
            // Crea una instancia de Dialog Listener para que podamos enviar eventos al host
            mListener = (AlertDialogListener) context;
        } catch (ClassCastException e) {
            // La actividad no implementa la interfaz, lanza una excepción
            throw new ClassCastException(context.toString()
                    + " must implement AlertDialogListener");
        }
    }


}