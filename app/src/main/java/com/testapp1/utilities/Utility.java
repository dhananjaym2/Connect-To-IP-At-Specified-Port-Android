package com.testapp1.utilities;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by dhananjay on 3/2/15.
 */
public class Utility {

	// public static Dialog mDialog;
	// public static ProgressDialog mProgressDialog;
	// private static String logTag = "Utility";
	//
	// /**
	// * LMSC app log for debugging
	// *
	// * @param type
	// * @param TAG
	// * @param message
	// */
	// public static void debugLog(int type, String TAG, String message) {
	// switch (type) {
	// case 0:
	// Log.i(TAG, message);
	// break;
	// case 1:
	// Log.d(TAG, message);
	// break;
	// case 2:
	// Log.e(TAG, message);
	// break;
	// case 3:
	// Log.v(TAG, message);
	// break;
	// case 4:
	// Log.w(TAG, message);
	// break;
	// default:
	// Log.d(TAG, message);
	// break;
	// }
	// }
	//
	// public static void showAlert(final Context mContext, String
	// strAlertMessage,
	// String strBtnText, boolean isCancellable, View.OnClickListener
	// mOnClickListener) {
	// if (mDialog != null) {
	// if (mDialog.isShowing()) {
	// mDialog.dismiss();
	// }
	// mDialog = null;
	// }
	// mDialog = new Dialog(mContext, R.style.Dialog_No_Border);
	// mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	// mDialog.setContentView(R.layout.alert_no_header);
	// mDialog.setCancelable(isCancellable);
	// TextView txtAlertText_no_header = (TextView)
	// mDialog.findViewById(R.id.txtAlertText_no_header);
	// Button btnAlert_no_header = (Button)
	// mDialog.findViewById(R.id.btnAlert_no_header);
	// if (mOnClickListener != null) {
	// btnAlert_no_header.setOnClickListener(mOnClickListener);
	// } else {
	// btnAlert_no_header.setOnClickListener(new View.OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// mDialog.dismiss();
	// }
	// });
	// }
	// txtAlertText_no_header.setText(strAlertMessage);
	// if (strBtnText != null) {
	// btnAlert_no_header.setText(strBtnText);
	// }
	// try {
	// mDialog.show();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	//
	// // public static void ShowFragment(Fragment mFragment, Bundle mBundle,
	// // Activity mActivity, String TAG) {
	// // if (mBundle != null) {
	// // mFragment.setArguments(mBundle);
	// // }
	// // Utility.debugLog(1, "BackStack", "getBackStackEntryCount(): "
	// // + ((MainActivityLMSC) mActivity)
	// // .getSupportFragmentManager().getBackStackEntryCount()
	// // + " TAG " + TAG);
	// //
	// // ((MainActivityLMSC) mActivity).getSupportFragmentManager()
	// // .beginTransaction()
	// // .replace(R.id.frame_mainactivity_lmsc, mFragment, TAG)
	// // .addToBackStack(TAG).commit();
	// // }
	//
	//
	// /**
	// * Method to check email is valid or not
	// *
	// * @param strEmailAddress
	// * @return true if email is valid else false
	// */
	// public static boolean emailValidator(String strEmailAddress) {
	// Matcher mMatcher;
	// mMatcher = LMSCConstants.EMAIL_ADDRESS_PATTERN.matcher(strEmailAddress);
	// Log.d("test", "email mMatcher.matches() result:" + mMatcher.matches());
	// return mMatcher.matches();
	// }
	//
	// /**
	// * method to check if internet is connected or not
	// *
	// * @param mContext
	// * @return true if connected else false
	// */
	// public static boolean isInternetAvailable(Context mContext) {
	//
	// ConnectivityManager cm = (ConnectivityManager) mContext
	// .getSystemService(Context.CONNECTIVITY_SERVICE);
	//
	// NetworkInfo wifiNetwork = cm
	// .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	// if (wifiNetwork != null && wifiNetwork.isConnected()) {
	// return true;
	// }
	//
	// NetworkInfo mobileNetwork = cm
	// .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
	// if (mobileNetwork != null && mobileNetwork.isConnected()) {
	// return true;
	// }
	//
	// NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
	// if (activeNetwork != null && activeNetwork.isConnected()) {
	// return true;
	// }
	//
	// return false;
	// }
	//
	// /**
	// * static method to SHOW progress dialog for LMSC App.
	// * <p/>
	// * use Utility.hideProgressDialog(mContext)to hide this dialog.
	// *
	// * @param mContext
	// * @param strMessageOnProgressDialog
	// */
	// public static void showProgressDialog(Context mContext, String
	// strMessageOnProgressDialog) {
	// if (mProgressDialog != null) {
	// if (mProgressDialog.isShowing()) {
	// mProgressDialog.dismiss();
	// }
	// }
	// // mProgressDialog = new ProgressDialog(mContext);
	// // mProgressDialog.setMessage(strLargeText);
	// // mProgressDialog.setIndeterminate(false);
	// // mProgressDialog.setCancelable(false);
	// // mProgressDialog.show();
	// if (mProgressDialog == null)
	// mProgressDialog = new ProgressDialog(mContext);
	// if (strMessageOnProgressDialog != null) {
	// mProgressDialog.setMessage(strMessageOnProgressDialog);
	// } else {
	// mProgressDialog.setMessage(mContext.getString(R.string.LoadingProgressDialog));
	// }
	// mProgressDialog.setCancelable(false);
	// try {
	// mProgressDialog.show();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// /**
	// * static method to HIDE progress dialog for LMSC App.
	// *
	// * @param mContext
	// */
	// public static void hideProgressDialog(Context mContext) {
	// if (mProgressDialog != null) {
	// if (mProgressDialog.isShowing()) {
	// mProgressDialog.dismiss();
	// }
	// }
	// }
	//
	// /**
	// * Method to Hide Soft Input Keyboard
	// *
	// * @param act
	// */
	// public static void HideInputKeypad(Activity act) {
	//
	// InputMethodManager inputManager = (InputMethodManager) act
	// .getSystemService(Context.INPUT_METHOD_SERVICE);
	//
	// if (act.getCurrentFocus() != null)
	// inputManager.hideSoftInputFromWindow(act.getCurrentFocus()
	// .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	//
	// }
	//
	// /**
	// * Method to show dialog popup for selecting source to upload picture
	// */
	// public static void uploadPic_Alert(final Context mContext,
	// final AlertDialogClickInterface mAlertDialogInterface,
	// final String strClassName) {
	// final Dialog dialog;
	// // LinearLayout m_llMain;
	// TextView camera, gallery;
	// Button dismiss_dialog;
	//
	// dialog = new Dialog(mContext, R.style.Dialog_No_Border);
	// dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	// dialog.setContentView(R.layout.alert_photo_select_action);
	//
	// // // LayoutInflater m_inflater = LayoutInflater.from(getActivity());
	// // // View m_view = m_inflater.inflate(R.layout.cd_layout, null);
	// // m_llMain = (LinearLayout) dialog.findViewById(R.id.cadllMain);
	// // m_llMain.setBackgroundResource(R.drawable.custom_dialog_bg);
	// // TextView Title = (TextView) dialog.findViewById(R.id.title);
	// camera = (TextView) dialog.findViewById(R.id.txtCamera_photo_select);
	// gallery = (TextView) dialog.findViewById(R.id.txtGallery_photo_select);
	// dismiss_dialog = (Button)
	// dialog.findViewById(R.id.btnAlert_Dismiss_photo_select);
	// // Title.setText(mActivity.getResources().getString(
	// // R.string.tag_dialog_title));
	// dismiss_dialog.setOnClickListener(new View.OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// dialog.dismiss();
	// }
	// });
	// gallery.setOnClickListener(new View.OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// mAlertDialogInterface.setOnReceiveDialogResult(strClassName,
	// mContext.getString(R.string.Gallery));
	// dialog.dismiss();
	// }
	// });
	// camera.setOnClickListener(new View.OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// // takePicture(mActivity);
	// mAlertDialogInterface.setOnReceiveDialogResult(strClassName,
	// mContext.getString(R.string.Camera));
	// dialog.dismiss();
	// }
	// });
	// dialog.show();
	// }
	//
	// /**
	// * creates a new image file <*.png> and returns its name
	// *
	// * @return strNewImageFileName
	// */
	// public static String getImageName() {
	// String fileName = null;
	// String filepaths =
	// Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
	// + "/LMSC_images/";
	// // Environment.getExternalStorageDirectory() + File.separator +
	// "LMSC_images/";
	//
	// File myDir = new File(filepaths);
	// if (myDir.exists() == false)
	// myDir.mkdirs();
	//
	// Calendar c = Calendar.getInstance();
	// SimpleDateFormat sdf = new SimpleDateFormat("dd:MMMM:yyyy_hh:mm:ss a");
	// String strDate = sdf.format(c.getTime());
	//
	// DateFormat inputFormatter = new SimpleDateFormat(
	// "dd:MMMM:yyyy_hh:mm:ss a");
	// Date date = null;
	// try {
	// date = inputFormatter.parse(strDate);
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	// DateFormat outputFormatter = new SimpleDateFormat(
	// "dd_MM_yyyy_hh_mm_ss_a");
	// String output = outputFormatter.format(date);
	//
	// fileName = "LMSC_profile_photo" + output + ".jpg";
	// return filepaths + fileName;
	// }
	//
	// public static Bitmap resize_photo_sameHeightAndWidth(Bitmap
	// CroppedBitmap) {
	// if (CroppedBitmap != null) {
	// Bitmap centerCropBitmap;
	// if (CroppedBitmap.getWidth() >= CroppedBitmap.getHeight()) {
	//
	// centerCropBitmap = Bitmap.createBitmap(
	// CroppedBitmap,
	// CroppedBitmap.getWidth() / 2
	// - CroppedBitmap.getHeight() / 2, 0,
	// CroppedBitmap.getHeight(), CroppedBitmap.getHeight());
	//
	// System.out.println("centerCropBitmap height : "
	// + centerCropBitmap.getHeight());
	// System.out.println("centerCropBitmap width : "
	// + centerCropBitmap.getWidth());
	//
	// } else {
	//
	// centerCropBitmap = Bitmap.createBitmap(
	// CroppedBitmap,
	// 0,
	// CroppedBitmap.getHeight() / 2
	// - CroppedBitmap.getWidth() / 2,
	// CroppedBitmap.getWidth(), CroppedBitmap.getWidth());
	//
	// System.out.println("centerCropBitmap height : "
	// + centerCropBitmap.getHeight());
	// System.out.println("centerCropBitmap width : "
	// + centerCropBitmap.getWidth());
	//
	// }
	// return centerCropBitmap;
	// } else {
	// return null;
	// }
	// }
	//
	// /**
	// * method to get Bitmap From Data Intent
	// * <p/>
	// * params for GALLERY: mContext, data, null, false
	// * <p/>
	// * params for CAMERA: null, null, fileName, true
	// *
	// * @param mContext
	// * @param data Intent object which is received in onActivityResult
	// * @param filePath required only when using camera.
	// * @param isFromCamera true if create bitmap through camera else false
	// from gallery.
	// * @return Bitmap object of camera-clicked or Gallery-selected image
	// */
	// @SuppressLint("NewApi")
	// public static Bitmap getBitmapFromDataIntent(Context mContext, Intent
	// data,
	// String filePath, boolean isFromCamera
	// /*,Seren_AlertDialog mSeren_AlertDialog*/) {
	// Bitmap photoBitmap = null;
	// if (isFromCamera) {
	// /**
	// * in case of camera
	// */
	// try {
	// // photoBitmap = BitmapFactory.decodeFile(filePath);
	// /**
	// * rotation code starts
	// */
	// int orientation = 0;
	// try {
	// ExifInterface ei = new ExifInterface(filePath);
	// orientation = ei.getAttributeInt(
	// ExifInterface.TAG_ORIENTATION,
	// ExifInterface.ORIENTATION_NORMAL);
	// Utility.debugLog(1, "Utility", "orientation:" + orientation);
	// } catch (IOException e) {
	// e.printStackTrace();
	// Utility.showAlert(mContext, mContext.getResources()
	// .getString(R.string.ErrorTag_TooLargeImage)
	// , null, false, null);
	// }
	// // photoBitmap = BitmapFactory.decodeFile(filePath);
	// /* dj changes 11feb2015 to avoid out of memory... commented above and
	// added following*/
	// File file = new File(filePath);
	// photoBitmap = DecodeFile.decodeFile(file);
	//
	// switch (orientation) {
	// case ExifInterface.ORIENTATION_ROTATE_90:
	// photoBitmap = Utility.RotateImage(90, photoBitmap);
	// break;
	//
	// case ExifInterface.ORIENTATION_ROTATE_180:
	// photoBitmap = Utility.RotateImage(180, photoBitmap);
	// break;
	//
	// case ExifInterface.ORIENTATION_ROTATE_270:
	// photoBitmap = Utility.RotateImage(270, photoBitmap);
	// break;
	// }
	// /**
	// * rotation code ends
	// */
	// } catch (OutOfMemoryError e) {
	// Utility.showAlert(mContext, mContext.getResources()
	// .getString(R.string.ErrorTag_TooLargeImage),
	// null, false, null);
	// // mSeren_AlertDialog.showAlertDialog(mContext.getResources()
	// // .getString(R.string.ErrorTag_TooLargeImage));
	// e.printStackTrace();
	// } catch (Exception e) {
	// Utility.debugLog(1, "Utility", "Error while reading file");
	// e.printStackTrace();
	// Utility.showAlert(mContext, mContext.getResources()
	// .getString(R.string.ErrorTag_IncorrectImage)
	// , null, false, null);
	// // mSeren_AlertDialog.showAlertDialog(mContext.getResources()
	// // .getString(R.string.ErrorTag_IncorrectImage));
	// }
	// } else {
	// /**
	// * in case of Gallery
	// */
	// Uri selectedImageUri = data.getData();
	// if (selectedImageUri != null
	// && !selectedImageUri.toString().startsWith(
	// /**
	// * to check Picasa files...
	// */
	// "content://com.google.android.gallery3d")) {
	// try {
	// String[] filePathColumn = {MediaStore.Images.Media.DATA};
	//
	// Cursor cursor = mContext.getContentResolver().query(
	// selectedImageUri, filePathColumn, null, null, null);
	// cursor.moveToFirst();
	//
	// int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	// filePath = cursor.getString(columnIndex);
	// cursor.close();
	//
	// File file = new File(filePath);
	//
	// photoBitmap = DecodeFile.decodeFile(file);
	// Utility.debugLog(1, "test", "size = " + sizeOf(photoBitmap));
	// } catch (Exception e) {
	// /**
	// * generally OutOfMemoryError
	// */
	// Utility.debugLog(1, "Utility", "selectedImageUri:" + selectedImageUri);
	// Utility.showAlert(mContext, mContext.getResources()
	// .getString(R.string.ErrorTag_IncorrectImage)
	// , null, false, null);
	// // mSeren_AlertDialog.showAlert(mContext.getResources()
	// // .getString(R.string.ErrorTag_IncorrectImage));
	// e.printStackTrace();
	// }
	// } else {
	// Utility.debugLog(1, "Utility", "selectedImageUri:" + selectedImageUri);
	// Utility.showAlert(mContext, mContext.getResources()
	// .getString(R.string.ErrorTag_IncorrectImage)
	// , null, false, null);
	// // mSeren_AlertDialog.showAlertDialog(mContext.getResources()
	// // .getString(R.string.ErrorTag_IncorrectImage));
	// }
	// }
	// // /**
	// // * image compression
	// // */
	// // if (photoBitmap != null) {
	// // File file = new File(filePath);
	// // long originalSize = file.length();
	// // Log.d("test", "originalSize = " + originalSize + ", photoSize:"
	// // + photoBitmap.getByteCount());
	// //
	// // final BitmapFactory.Options options = new BitmapFactory.Options();
	// // options.inJustDecodeBounds = true;
	// //
	// // BitmapFactory.decodeFile(filePath, options);
	// //
	// // // Calculate inSampleSize based on a preset ratio
	// // options.inSampleSize = 5;
	// //
	// // // Decode bitmap with inSampleSize set
	// // options.inJustDecodeBounds = false;
	// //
	// // photoBitmap = BitmapFactory.decodeFile(filePath, options);
	// //
	// // Log.d("test",
	// // "NewSize after compressing: " + photoBitmap.getByteCount());
	// // }
	// Utility.debugLog(1, "Utility",
	// "before resize_photo_sameHeightAndWidth()");
	// Utility.resize_photo_sameHeightAndWidth(photoBitmap);
	// return photoBitmap;
	// }
	//
	// public static int sizeOf(Bitmap data) {
	// if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1) {
	// return data.getRowBytes() * data.getHeight();
	// } else {
	// return data.getByteCount();
	// }
	// }
	//
	// /**
	// * Function to rotate image according to angle
	// *
	// * @param angle
	// * @param mBitmap
	// * @return
	// */
	// public static Bitmap RotateImage(int angle, Bitmap mBitmap) {
	// Matrix matrix = new Matrix();
	// matrix.postRotate(angle);
	// mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(),
	// mBitmap.getHeight(), matrix, true);
	// return mBitmap;
	// }
	//
	public static void generateHashKeyForFacebook(Context mContext) {
		try {
			PackageInfo info =
			/* getActivity() */mContext.getPackageManager().getPackageInfo(
					"com.lmsc", PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures) {
				MessageDigest md;
				md = MessageDigest.getInstance("SHA");
				// md.update(signature.toByteArray());
				String something = new String(Base64.encode(md.digest(), 0));
				// String something = new
				// String(Base64.encodeBytes(md.digest()));
				Log.e("hash key", something);
			}
		} catch (PackageManager.NameNotFoundException e1) {
			Log.e("name not found", e1.toString());
		} catch (NoSuchAlgorithmException e) {
			Log.e("no such an algorithm", e.toString());
		} catch (Exception e) {
			Log.e("exception", e.toString());
		}
	}
	//
	// public static void initSpinnerSex(Context mContext, Spinner spinnerName)
	// {
	// String strArr[] = new String[]{"Sex", "Male", "Female"};
	// ArrayList<ProvinceModel> al_SexSpinner = new ArrayList<>();
	// ProvinceModel modelObj;
	// for (int i = 0; i <= 2; i++) {
	// modelObj = new ProvinceModel();
	// modelObj.setProvince_full_name(strArr[i]);
	// al_SexSpinner.add(modelObj);
	// }
	// ProvinceSpinnerAdapter mSexSpinnerAdapter = new
	// ProvinceSpinnerAdapter(mContext, al_SexSpinner);
	// spinnerName.setAdapter(mSexSpinnerAdapter);
	// }
	//
	// public static String getOfferDateTime(String strDateTime) {
	// try {
	//
	// DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// Date date = inputDateFormat.parse(strDateTime);//"2014-12-31 19:30:00"
	//
	// DateFormat outputDateFormat = new
	// SimpleDateFormat("dd/MMM/yyyy HH:mm");//previous MMM. dd, yyyy
	// String outputDate = outputDateFormat.format(date);
	//
	// // Utility.debugLog(1, "Utility", "formatted outputDate:" + outputDate);
	// return outputDate;
	// } catch (Exception e) {
	// e.printStackTrace();
	// return strDateTime;
	// }
	// }
	//
	// public static String getFormattedDateTime(String strDateTime) {
	// String outputDate = "";
	// try {
	//
	// DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// Date date = inputDateFormat.parse(strDateTime);//"2014-12-31 19:30:00"
	//
	// // DateFormat outputDateFormat = new
	// SimpleDateFormat("dd MMM. yyyy HH:mm");
	// // String outputDate = outputDateFormat.format(date);
	//
	// try {
	// // java.util.Date date1;
	// // date1 = inputFormatter.parse(serverdate);
	//
	// DateFormat outputFormatter = new SimpleDateFormat(
	// "dd MMM yyyy");
	// String output = outputFormatter.format(date);
	//
	// String[] splitday = output.split(" ");
	//
	// // Log.d("TABC", "date o/p" + splitday[0]);
	//
	// String dayprifix = "";
	// // if(splitday.length>2)
	// // {
	//
	// if (Integer.parseInt(splitday[0]) >= 11
	// && Integer.parseInt(splitday[0]) <= 13) {
	// dayprifix = "th";
	// }
	// switch (Integer.parseInt(splitday[0]) % 10) {
	// case 1:
	// if (dayprifix.equals("")) {
	// dayprifix = "st";
	// }
	// break;
	// case 2:
	// dayprifix = "nd";
	// break;
	// case 3:
	// dayprifix = "rd";
	// break;
	// default:
	// dayprifix = "th";
	// break;
	// }
	//
	// outputDate = splitday[0] + ""
	// + dayprifix + " " + splitday[1] + ". "
	// + splitday[2];
	//
	// // }
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }
	//
	// // Utility.debugLog(1, "Utility", "formatted outputDate:" + outputDate);
	// return outputDate;
	// } catch (Exception e) {
	// e.printStackTrace();
	// return strDateTime;
	// }
	// }
	//
	// /**
	// * method for debug log equivalent to Log.d()
	// *
	// * @param logTag
	// * @param logMsg
	// */
	// public static void d(String logTag, String logMsg) {
	// Utility.debugLog(1, logTag, logMsg);
	// }
	//
	// public static Bitmap convertToBitmap(Drawable drawable, int widthPixels,
	// int heightPixels) {
	// Bitmap mutableBitmap = Bitmap.createBitmap(widthPixels, heightPixels,
	// Bitmap.Config.ARGB_8888);
	// Canvas canvas = new Canvas(mutableBitmap);
	// drawable.setBounds(0, 0, widthPixels, heightPixels);
	// drawable.draw(canvas);
	// return mutableBitmap;
	// }
	//
	// /**
	// * method to show toast msg
	// *
	// * @param applicationContext
	// * @param strToastMsg
	// */
	// public static void showToast(Context applicationContext, String
	// strToastMsg) {
	// Toast.makeText(applicationContext, strToastMsg,
	// Toast.LENGTH_SHORT).show();
	// }
	//
	// /**
	// * Method to show dialog popup for selecting source to upload picture
	// */
	// public static void socialShare_Alert(final Context mContext,
	// final AlertDialogClickInterface mAlertDialogInterface,
	// final String strClassName) {
	// final Dialog dialog;
	// // LinearLayout m_llMain;
	// TextView txtSMS_alert_share, txtSHARE_WITH_FACEBOOK_alert_share,
	// txtSHARE_WITH_TWITTER_alert_share;
	// Button btnCancel_alert_share;
	//
	// dialog = new Dialog(mContext, R.style.Dialog_No_Border);
	// dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	// dialog.setContentView(R.layout.alert_share);
	//
	// // // LayoutInflater m_inflater = LayoutInflater.from(getActivity());
	// // // View m_view = m_inflater.inflate(R.layout.cd_layout, null);
	// // m_llMain = (LinearLayout) dialog.findViewById(R.id.cadllMain);
	// // m_llMain.setBackgroundResource(R.drawable.custom_dialog_bg);
	// // TextView Title = (TextView) dialog.findViewById(R.id.title);
	// txtSMS_alert_share = (TextView)
	// dialog.findViewById(R.id.txtSMS_alert_share);
	// txtSHARE_WITH_FACEBOOK_alert_share = (TextView) dialog.findViewById(R.id.
	// txtSHARE_WITH_FACEBOOK_alert_share);
	// txtSHARE_WITH_TWITTER_alert_share = (TextView) dialog.findViewById(R.id.
	// txtSHARE_WITH_TWITTER_alert_share);
	// btnCancel_alert_share = (Button)
	// dialog.findViewById(R.id.btnCancel_alert_share);
	// // Title.setText(mActivity.getResources().getString(
	// // R.string.tag_dialog_title));
	// btnCancel_alert_share.setOnClickListener(new View.OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// dialog.dismiss();
	// }
	// });
	// txtSMS_alert_share.setOnClickListener(new View.OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// mAlertDialogInterface.setOnReceiveDialogResult(strClassName,
	// mContext.getString(R.string.SMS));
	// dialog.dismiss();
	// }
	// });
	// txtSHARE_WITH_FACEBOOK_alert_share.setOnClickListener(new
	// View.OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// // takePicture(mActivity);
	// mAlertDialogInterface.setOnReceiveDialogResult(strClassName,
	// mContext.getString(R.string.SHARE_WITH_FACEBOOK));
	// dialog.dismiss();
	// }
	// });
	// txtSHARE_WITH_TWITTER_alert_share.setOnClickListener(new
	// View.OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// // takePicture(mActivity);
	// mAlertDialogInterface.setOnReceiveDialogResult(strClassName,
	// mContext.getString(R.string.SHARE_WITH_TWITTER));
	// dialog.dismiss();
	// }
	// });
	// dialog.show();
	// }
	//
	// public static boolean isTwitterLoggedInAlready(LMSCSharedPreferences
	// mLMSCSharedPreferences) {
	// // return twitter login status from Shared Preferences
	// Utility.debugLog(1, "sushil", "isTwitterLoggedInAlready()");
	// return mLMSCSharedPreferences.getBooleanValue(
	// LMSCConstants.PREF_KEY_TWITTER_LOGIN, false);
	// }
	//
	// /**
	// * This method use PackageManager Class to check for Twitter
	// * package if it is installed in the device or not.
	// */
	// public static boolean isAppInstalled(int packageName, Context
	// applicationContext) {
	//
	// boolean is_app_installed = false;
	// try {
	// @SuppressWarnings("unused")
	// ApplicationInfo info =
	// applicationContext.getPackageManager().getApplicationInfo(
	// applicationContext.getResources().getString(packageName), 0);
	// Utility.debugLog(1, "Utility", "tw info in app installed");
	//
	// is_app_installed = true;
	// } catch (PackageManager.NameNotFoundException e) {
	// is_app_installed = false;
	// }
	// Utility.debugLog(1, "sushil", "isAppInstalled():" + is_app_installed);
	// return is_app_installed;
	// }
	//
	// public static void feedbackAlert(final Context mContext, final
	// AlertDialogClickInterface
	// mAlertDialogInterface, final String strClassName, final
	// ArrayList<ReasonsModel_Feedback>
	// al_ReasonsModel_Feedback) {
	// final Dialog dialog;
	// Button btnDISMISS_alertReason, btnSUBMIT_alertReason;
	// final ListView listView_alertReason;
	// View.OnClickListener mListener;
	//
	// dialog = new Dialog(mContext, R.style.Dialog_No_Border);
	// dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	// dialog.setContentView(R.layout.alert_reason);
	//
	// mListener = new View.OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// Utility.d(logTag, "in onClickListener of Utility.feedbackAlert");
	// }
	// };
	//
	// // listView_alertReason = (ListView)
	// dialog.findViewById(R.id.listView_alertReason);
	// // final Reason_Adapter_ListView mAdapter = new
	// Reason_Adapter_ListView(mContext,
	// // al_ReasonsModel_Feedback, mListener);
	// // listView_alertReason.setAdapter(mAdapter);
	// // listView_alertReason.setOnItemClickListener(new
	// AdapterView.OnItemClickListener() {
	// // @Override
	// // public void onItemClick(AdapterView<?> parent, View view, int
	// position, long id) {
	// //
	// // Utility.d(logTag, "in onItemClick()" + position);
	// // if (al_ReasonsModel_Feedback.get(position).getIsSelected()
	// // .equals("0")) {
	// // /**
	// // * item is selected now
	// // */
	// // for (int i = 0; i < al_ReasonsModel_Feedback.size(); i++) {
	// // if (i == position) {
	// // al_ReasonsModel_Feedback.get(position).setIsSelected("1");
	// // } else {
	// // al_ReasonsModel_Feedback.get(i).setIsSelected("0");
	// // }
	// // }
	// //
	// // } else if (al_ReasonsModel_Feedback.get(position).getIsSelected()
	// // .equals("1")) {
	// // /**
	// // * un-select this item... as it was selected earlier
	// // */
	// //
	// // for (int i = 0; i < al_ReasonsModel_Feedback.size(); i++) {
	// // al_ReasonsModel_Feedback.get(i).setIsSelected("0");
	// // }
	// // }
	// // /**
	// // * refresh adapter to set item selected or un-selected
	// // */
	// // mAdapter.notifyDataSetChanged();
	// // listView_alertReason.invalidate();
	// // }
	// // });
	// btnSUBMIT_alertReason = (Button)
	// dialog.findViewById(R.id.btnSUBMIT_alertReason);
	// btnDISMISS_alertReason = (Button)
	// dialog.findViewById(R.id.btnDISMISS_alertReason);
	// // Title.setText(mActivity.getResources().getString(
	// // R.string.tag_dialog_title));
	// btnDISMISS_alertReason.setOnClickListener(new View.OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// dialog.dismiss();
	// }
	// });
	// btnSUBMIT_alertReason.setOnClickListener(new View.OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// mAlertDialogInterface.setOnReceiveDialogResult(strClassName,
	// mContext.getString(R.string.SUBMIT));
	// dialog.dismiss();
	// }
	// });
	// dialog.show();
	// }
	//
	// /**
	// * Method to show dialog popup for selecting source to upload picture
	// */
	// public static void notification_Alert(final Context mContext,
	// final AlertDialogClickInterface mAlertDialogInterface,
	// final String strClassName, String strNotificationTopic,
	// String strNotificationData) {
	// final Dialog dialog;
	// // LinearLayout m_llMain;
	// TextView txtNotificationData, txtNotificationTopic;
	// Button btnDismissAlert;
	//
	// dialog = new Dialog(mContext, R.style.Dialog_No_Border);
	// dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	// dialog.setContentView(R.layout.alert_offer);
	//
	// // // LayoutInflater m_inflater = LayoutInflater.from(getActivity());
	// // // View m_view = m_inflater.inflate(R.layout.cd_layout, null);
	// // m_llMain = (LinearLayout) dialog.findViewById(R.id.cadllMain);
	// // m_llMain.setBackgroundResource(R.drawable.custom_dialog_bg);
	// // TextView Title = (TextView) dialog.findViewById(R.id.title);
	// txtNotificationData = (TextView)
	// dialog.findViewById(R.id.txtNotificationData);
	// txtNotificationData.setText(strNotificationData.trim());
	//
	// txtNotificationTopic = (TextView)
	// dialog.findViewById(R.id.txtNotificationTopic);
	// txtNotificationTopic.setText(strNotificationTopic.trim());
	//
	// btnDismissAlert = (Button) dialog.findViewById(R.id.btnDismissAlert);
	// // Title.setText(mActivity.getResources().getString(
	// // R.string.tag_dialog_title));
	// btnDismissAlert.setOnClickListener(new View.OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// dialog.dismiss();
	// }
	// });
	// txtNotificationData.setOnClickListener(new View.OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// // Uncomment this to handle its click
	// // mAlertDialogInterface.setOnReceiveDialogResult(strClassName,
	// // LMSCConstants.KEY_OfferDetails_Notification);
	// // dialog.dismiss();
	// }
	// });
	//
	// if (dialog.isShowing())
	// dialog.dismiss();
	//
	// dialog.show();
	// }
	//
	//
	// /**
	// * Function to show settings alert dialog
	// * On pressing Settings button will lauch Settings Options
	// */
	// public static void showSettingsAlert(final Context mContext) {
	// AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
	//
	// // Setting Dialog Title
	// alertDialog.setTitle(R.string.app_name);
	// alertDialog.setCancelable(false);
	//
	// // Setting Dialog Message
	// alertDialog.setMessage(/*(R.string.LocationNotAvailable) +
	// */R.string.turnOnGps);
	//
	// // On pressing Settings button
	// alertDialog.setPositiveButton(R.string.SETTINGS, new
	// DialogInterface.OnClickListener() {
	// public void onClick(DialogInterface dialog, int which) {
	// Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	// mContext.startActivity(intent);
	// }
	// });
	//
	// // // on pressing cancel button
	// // alertDialog.setNegativeButton(R.string.CANCEL, new
	// DialogInterface.OnClickListener() {
	// // public void onClick(DialogInterface dialog, int which) {
	// // dialog.cancel();
	// // }
	// // });
	//
	// // Showing Alert Message
	// alertDialog.show();
	// }
	//
}