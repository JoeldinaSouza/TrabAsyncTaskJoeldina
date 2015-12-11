package android_3bim.ifpb.edu.br.loginasynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.security.ProtectionDomain;

/**
 * Created by IFPB on 10/12/2015.
 */
public class TesteAsyncTask extends AsyncTask <Void, Integer, String> {
    Context context;
    TextView textView;
    Button button;
    ProgressDialog progressDialog;
    TesteAsyncTask(Context context,TextView textView,Button button)
    {
      this.context = context;
      this.textView = textView;
      this.button = button;
    }




    @Override
    protected String doInBackground(Void... params) {
        int i = 0;
        synchronized (this)
        {

            while (i<10)
            {
                try {
                    wait(1500);
                    i++;
                    publishProgress(i);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }


            }


        }
        return "Carregando complete...";
    }

    @Override
    protected void onPreExecute(){
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Download carregando...");
        progressDialog.setMax(10);
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String result){
        textView.setText(result);
        button.setEnabled(true);
        progressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        int progress = values[0];
        progressDialog.setProgress(progress);
        textView.setText(" Download carregando...");
    }
}
