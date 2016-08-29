package com.pandocloud.freeiot.ui.device;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.xwalk.core.XWalkView;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pandocloud.android.utils.StringUtil;
import com.pandocloud.freeiot.R;
import com.pandocloud.freeiot.jsbridge.BridgeHelper;

public class TestDeviceActivity extends Activity {
	String tag = "TestDeviceActivity";
	private String mIdentifier;
	private BridgeHelper bridgeHelper;

	public XWalkView mWebView;
	private String mUrl;

	Button btnOpen;
	Button btnClose;
	Button btnColor;
	Button btnBack;

	int open_state = 1;
	int close_state = 0;
	String app_key_content = "b923952a29e0126d10bb424e741d71cd72879e8220f3f77e670d7133daaa5cec";
	String app_key_header = "App-Key";
	String control_url = "http://120.24.46.148:8888/application/v1/devices/0-8-18fe34d2c162/status";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_test_device);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			mIdentifier = bundle.getString("identifier");
			// operateUrl = String.format(operateUrl, mIdentifier);
			// mUrl = bundle.getString("app");
			// if (BridgeUtil.hasAssetFile(this, "index.html")) {
			// mUrl = "file:///android_asset/index.html";
			// }
			// LogUtils.e("url=> " + mUrl);
			// mUrl = "file:///android_asset/index.html";
			// mUrl = "file:///android_asset/failed/404.html";
			// mTitleName = bundle.getString("name");
			// ((TextView)findViewById(R.id.title)).setText(mTitleName);
		}
		initViews();
		//

	}

	private void initViews() {
		btnOpen = (Button) findViewById(R.id.btn_control_open);
		btnClose = (Button) findViewById(R.id.btn_control_close);
		btnColor = (Button) findViewById(R.id.btn_control_color);
		btnBack = (Button) findViewById(R.id.btn_back);
		
		btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		btnColor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				controlColor(new ControlCallBack() {

					@Override
					public void onSuccess(final String resultStr) {
						// TODO Auto-generated method stub
						if (!TextUtils.isEmpty(resultStr)) {
							runOnUiThread(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									Toast.makeText(getApplicationContext(),
											"返回结果：" + resultStr,
											Toast.LENGTH_LONG).show();
								}
							});
						}
					}

					@Override
					public void onFailed(final String resultStr) {
						// TODO Auto-generated method stub
						if (!TextUtils.isEmpty(resultStr)) {
							runOnUiThread(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									Toast.makeText(getApplicationContext(),
											"返回结果：" + resultStr,
											Toast.LENGTH_LONG).show();
								}
							});
						}
					}
				});
			}
		});
		
		btnOpen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				controlCommand(open_state, new ControlCallBack() {

					@Override
					public void onSuccess(final String resultStr) {
						// TODO Auto-generated method stub
						if (!TextUtils.isEmpty(resultStr)) {
							runOnUiThread(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									Toast.makeText(getApplicationContext(),
											"返回结果：" + resultStr,
											Toast.LENGTH_LONG).show();
								}
							});
						}
					}

					@Override
					public void onFailed(final String resultStr) {
						// TODO Auto-generated method stub
						if (!TextUtils.isEmpty(resultStr)) {
							runOnUiThread(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									Toast.makeText(getApplicationContext(),
											"返回结果：" + resultStr,
											Toast.LENGTH_LONG).show();
								}
							});
						}
					}
				});
			}
		});
		btnClose.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				controlCommand(close_state, new ControlCallBack() {

					@Override
					public void onSuccess(final String resultStr) {
						// TODO Auto-generated method stub
						if (!TextUtils.isEmpty(resultStr)) {
							runOnUiThread(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									Toast.makeText(getApplicationContext(),
											"返回结果：" + resultStr,
											Toast.LENGTH_LONG).show();
								}
							});
						}
					}

					@Override
					public void onFailed(final String resultStr) {
						// TODO Auto-generated method stub
						if (!TextUtils.isEmpty(resultStr)) {
							runOnUiThread(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									Toast.makeText(getApplicationContext(),
											"返回结果：" + resultStr,
											Toast.LENGTH_LONG).show();
								}
							});
						}
					}
				});
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_device, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	interface ControlCallBack {
		public void onSuccess(String msg);

		public void onFailed(String msg);
	}

	private void controlCommand(int command, final ControlCallBack call) {
		if (null == call) {
			throw new NullPointerException();
		}
		final int[] lameStates = new int[]{command};
		Thread controlThread = new Thread() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				HttpClient client = new DefaultHttpClient();
				HttpPut put = new HttpPut(control_url);
//				HttpPost post = new HttpPost(control_url);
				put.addHeader(app_key_header, app_key_content);
				Gson gson = new Gson();
				try {
					LameEntity lameEntity = new LameEntity();
					lameEntity.setPlug(lameStates);
					StringEntity entity = new StringEntity(gson.toJson(lameEntity, LameEntity.class), HTTP.UTF_8);
					entity.setContentType("application/json");
					put.setEntity(entity);
					HttpResponse response = client.execute(put);
					if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						final String resultStr = StringUtil
								.getStringFromInputStream(response.getEntity()
										.getContent());
						call.onSuccess(resultStr);
					} else {
						call.onFailed(String.valueOf(response.getStatusLine()
								.getStatusCode()));
					}
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					Log.e(tag, "ClientProtocolException", e);
					call.onFailed(e.getMessage());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					Log.e(tag, "IOException", e);
					call.onFailed(e.getMessage());
					// call.onFailed(String.valueOf(response.getStatusLine().getStatusCode()));
				}
			}

		};
		controlThread.start();
	}
	
	private void controlColor(final ControlCallBack call) {
		if (null == call) {
			throw new NullPointerException();
		}
		int red = (int)(Math.random()*100000);
		int green = (int)(Math.random()*100000);
		int blue = (int)(Math.random()*100000);
		final int[] colors = new int[]{red,green,blue};
		
		Thread controlThread = new Thread() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				HttpClient client = new DefaultHttpClient();
				HttpPut put = new HttpPut(control_url);
				put.addHeader(app_key_header, app_key_content);
				Gson gson = new Gson();
				ColorEntity colorEntity = new ColorEntity();
				colorEntity.setDsl307(colors);
				try {
					String jsonStr = gson.toJson(colorEntity, ColorEntity.class);
					Log.e(tag, "jsonStr = "+jsonStr);
//					String jsonStr2 = "{\"dsl307\":[250,250,250]}";
//					Log.e(tag, "jsonStr = "+jsonStr2);
					StringEntity entity = new StringEntity(jsonStr,HTTP.UTF_8);
					entity.setContentType("application/json");
					put.setEntity(entity);
					HttpResponse response = client.execute(put);
					if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						final String resultStr = StringUtil
								.getStringFromInputStream(response.getEntity()
										.getContent());
						call.onSuccess(resultStr);
					} else {
						call.onFailed(String.valueOf(response.getStatusLine()
								.getStatusCode()));
					}
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					Log.e(tag, "ClientProtocolException", e);
					call.onFailed(e.getMessage());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					Log.e(tag, "IOException", e);
					call.onFailed(e.getMessage());
					// call.onFailed(String.valueOf(response.getStatusLine().getStatusCode()));
				}
			}

		};
		controlThread.start();
	}
	class ColorEntity{
		int[] dsl307;

		public int[] getDsl307() {
			return dsl307;
		}

		public void setDsl307(int[] dsl307) {
			this.dsl307 = dsl307;
		}
		
	}
	class LameEntity{
		int[] plug;

		public int[] getPlug() {
			return plug;
		}

		public void setPlug(int[] plug) {
			this.plug = plug;
		}
		
	}
}
