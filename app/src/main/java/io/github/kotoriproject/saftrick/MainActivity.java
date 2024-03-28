package io.github.kotoriproject.saftrick;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.widget.Button;
import android.util.Log;
import abc.Abc;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button= findViewById(R.id.activity_main_haha);
        button.setOnClickListener((v)->{
            Intent ii=new Intent(Intent.ACTION_CREATE_DOCUMENT);
            ii.addCategory(Intent.CATEGORY_OPENABLE);
            ii.setType("*/*");

            startActivityForResult(ii, 12345);
        });
        Button buttonGo=findViewById(R.id.activity_main_test);
        buttonGo.setOnClickListener((v)->{
            Intent ii=new Intent(Intent.ACTION_CREATE_DOCUMENT);
            ii.addCategory(Intent.CATEGORY_OPENABLE);
            ii.setType("*/*");

            startActivityForResult(ii, 12346);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultData){
        if(requestCode == 12345 && resultCode == Activity.RESULT_OK){
            if(resultData != null){
                Uri uri=resultData.getData();
                ContentResolver c=getBaseContext().getContentResolver();
                if(uri == null) return;
                try(ParcelFileDescriptor pFd =c.openFileDescriptor(uri, "w")){
                    assert pFd != null;
                    int intFd=pFd.getFd();
                    long r= hello2(intFd);
                    pFd.close();
                    Log.i("hello",String.format("write size_t %d",r));
                    Log.i("hello",String.format("main activity pid:%d",android.os.Process.myPid()));
                }catch (Exception e){
                    Log.d("my",e.getMessage());
                }
            }
        }
        if(requestCode == 12346 && resultCode == Activity.RESULT_OK){
            if(resultData != null){
                Uri uri=resultData.getData();
                ContentResolver c=getBaseContext().getContentResolver();
                if(uri == null) return;
                try(ParcelFileDescriptor pFd =c.openFileDescriptor(uri, "w")){
                    assert pFd != null;
                    int intFd=pFd.detachFd();
                    long r=Abc.goHello(intFd);
                    Log.i("hello",String.format("write size_t %d",r));
                    Log.i("hello",String.format("main activity pid:%d",android.os.Process.myPid()));
                }catch (Exception e){
                    Log.d("my",e.getMessage());
                }
            }
        }
    }
    private native long hello2(int fd);
    static {
        System.loadLibrary("saftrick");
    }
}