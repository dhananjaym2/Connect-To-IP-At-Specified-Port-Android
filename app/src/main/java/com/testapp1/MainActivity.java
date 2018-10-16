package com.testapp1;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MainActivity extends Activity implements SurfaceHolder.Callback {
    private static final int REQUEST_VIDEO_CAPTURE = 1;
    Button btnCapture;
    SurfaceView mSurfaceView;
    SurfaceHolder mSurfaceHolder;
    private Camera camera;
    private static int cameraRotatedAngle;
    private Camera.PictureCallback jpegCallback;
    private String strFileName;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        btnCapture = (Button) findViewById(R.id.btnCapture);
//
//        btnCapture.setSelected(true);
//        String text = "<font color=#0000ff>Erste Farbe</font> <font color=#ff0000>zweite Farbe</font>";
//        btnCapture.setText(Html.fromHtml(text));
//        int color = Color.BLACK;
//        int r = (color >> 16) & 0xFF;
//        int g = (color >> 8) & 0xFF;
//        int b = (color >> 0) & 0xFF;
//        color = Color.rgb(r, g, b);
//        btnCapture.setTextColor(color);
//
//        TextView TV = (TextView) findViewById(R.id.txt_Facebook);
//        Spannable word = new SpannableString("Your message");
//
//        word.setSpan(new ForegroundColorSpan(Color.BLUE), 0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        TV.setText(word);
//        Spannable wordTwo = new SpannableString("Your new message");
//
//        wordTwo.setSpan(new ForegroundColorSpan(Color.RED), 0, wordTwo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        TV.append(wordTwo);
//        TV.setBackgroundColor(Color.rgb(0, 123, 123));

        initView();
    }

    private void initView() {
        mSurfaceView = (SurfaceView) findViewById(R.id.mSurfaceView);
        mSurfaceHolder = mSurfaceView.getHolder();

        /** install a surfaceHolder callback so that we get notified when the underlying surface is
         created and destroyed */
        mSurfaceHolder.addCallback(this);

        /** deprecated setting but required on Android versions prior to 3.0 */
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        jpegCallback = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                FileOutputStream outStream = null;
                try {
                    strFileName = getImageName();
                    outStream = new FileOutputStream(strFileName);
                    // getClickedImageOrientation();
                    rotateImage(cameraRotatedAngle);
                    outStream.write(data);
                    outStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "Saved at:" + strFileName,
                        Toast.LENGTH_LONG).show();
                refreshCamera();
            }
        };

        btnCapture = (Button) findViewById(R.id.btnCapture);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "btnCapture clicked");
//                captureImage();
//                dispatchTakeVideoIntent();
                openConnectActivity();
            }
        });
    }

    private void openConnectActivity() {
        Intent mIntent = new Intent(MainActivity.this, ConnectActivity.class);
        Log.d("test", "in openConnectActivity()");
        startActivity(mIntent);
    }

    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
//            mVideoView.setVideoUri(videoUri);
        }
    }

    public void rotateImage(int angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        if (bitmap != null)
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), matrix, true);
        else
            Log.d("test", "bitmap null");
    }

    protected void captureImage() {
        // take the picture
        if (camera != null) {
            camera.takePicture(null, null, jpegCallback);
        } else
            Log.d("test", "camera null in captureImage()");
    }

    /*
     * creates a new image file <*.png> and returns its name
     *
     * @return strNewImageFileName
     */
    public static String getImageName() {
        String fileName = null;
        String filepaths = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                + "/CameraUsingSurfaceView/";

        File myDir = new File(filepaths);
        if (myDir.exists() == false)
            myDir.mkdirs();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd:MMMM:yyyy_hh:mm:ss a",
                Locale.getDefault());
        String strDate = sdf.format(c.getTime());

        DateFormat inputFormatter = new SimpleDateFormat(
                "dd:MMMM:yyyy_hh:mm:ss a");
        Date date = null;
        try {
            date = inputFormatter.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat outputFormatter = new SimpleDateFormat(
                "dd_MM_yyyy_hh_mm_ss_a");
        String output = outputFormatter.format(date);

        fileName = "Image" + "_" + output + ".jpg";
        return filepaths + fileName;
    }


    private void refreshCamera() {
        if (mSurfaceHolder.getSurface() == null) {
            // preview surface does not exist
            return;
        }

        // stop preview before making changes
        try {
            camera.stopPreview();
        } catch (Exception e) {
            // ignore: tried to stop a non-existent preview
        }

        // set preview size and make any resize, rotate or reformatting changes
        // here start preview with new settings
        try {
            setCameraDisplayOrientation(MainActivity.this,
                    Camera.CameraInfo.CAMERA_FACING_BACK, camera);
            camera.setPreviewDisplay(mSurfaceHolder);
            camera.startPreview();
        } catch (Exception e) {

        }
    }

    public static void setCameraDisplayOrientation(Activity activity,
                                                   int cameraId, android.hardware.Camera camera) {
        android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        MainActivity.cameraRotatedAngle = degrees;
        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360; // compensate the mirror
        } else { // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("test", "surfaceCreated()");
        try {
            // open the camera
            camera = Camera.open();
        } catch (RuntimeException e) {
            // check for exceptions
            Log.d("test", "RuntimeException while initializing camera");
            e.printStackTrace();
            return;
            // } catch (Exception e) {
            // Log.d("test", "Error while initializing camera");
            // e.printStackTrace();
            // return;
        }
        Camera.Parameters param = null;
        if (camera != null) {
            param = camera.getParameters();

            // modify parameter
            List<Camera.Size> list_PictureSizes = param.getSupportedPictureSizes();//setPreviewSize(350, 280);
            for (int i = 0; i < list_PictureSizes.size(); i++) {
                Log.d("test", "size " + i + " : height:" + list_PictureSizes.get(i).height + " width:"
                        + list_PictureSizes.get(i).width);
                /*OUTPUT:
                 size 0 : height:1536 width:2048
                 size 1 : height:1152 width:2048
                 size 2 : height:1104 width:2048
                 size 3 : height:880 width:1632
                 size 4 : height:1200 width:1600
                 size 5 : height:864 width:1536
                 size 6 : height:960 width:1280
                 size 7 : height:720 width:1280
                 size 8 : height:672 width:1248
                 size 9 : height:720 width:960
                 size 10 : height:480 width:640
                 */
            }
            camera.setParameters(param);
            try {
                // The Surface has been created, now tell the camera where to
                // draw
                // the preview.
                // camera.setPreviewDisplay(surfaceHolder);
                if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                    Toast.makeText(MainActivity.this, "Camera not supported!", Toast.LENGTH_LONG).show();
                    return;
                }
                //SelectImage_Dialog.getWindow().getAttributes().windowAnimations=R.style.PauseDialogAnimation;
                camera.startPreview();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

        } else {
            Log.d("test", "camera null in surfaceCreated()");
            return;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d("test", "surfaceChanged()");
        // now that the size is known, set up the camera parameters and begin
        // the preview
        refreshCamera();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d("test", "surfaceDestroyed()");
        // stop preview and release camera
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        } else
            Log.d("test", "camera null in surfaceDestroyed()");
    }
}