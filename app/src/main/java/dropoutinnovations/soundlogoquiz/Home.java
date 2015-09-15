package dropoutinnovations.soundlogoquiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Home extends Activity {

	private Button mPlayButton;
	private ImageButton mShareButton;
	private Button mResetButton;
	final Context context = this;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		SharedPreferences pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
		final SharedPreferences.Editor editor = pref.edit();
		final SharedPreferences pagenumber = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
		final SharedPreferences.Editor changer = pagenumber.edit();
		final SharedPreferences hints = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
		final SharedPreferences.Editor hintchanger = hints.edit();
		final SharedPreferences correctcounter = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
		final SharedPreferences.Editor counterchanger = correctcounter.edit();

		SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);

		if (settings.getBoolean("my_first_time", true)) {
			editor.putInt("SoundNumber", 0);
			editor.commit();

			changer.putInt("PageNumber", 0);
			changer.commit();

			hintchanger.putInt("hints", 10);
			hintchanger.commit();

			counterchanger.putInt("correctno", 0);
			counterchanger.commit();


			settings.edit().putBoolean("my_first_time", false).commit();
		}




		mPlayButton = (Button) findViewById(R.id.play_button);
		mPlayButton.setOnClickListener(new View.OnClickListener() {@Override
																   public void onClick(View v) {
			int pageno = pagenumber.getInt("PageNumber", 0);
			if (pageno == 0) {
				Intent i = new Intent(Home.this, Levels.class);
				startActivity(i);
			} else if (pageno == 1) {
				Intent i = new Intent(Home.this, Hint.class);
				startActivity(i);
			} else if (pageno == 2) {
				Intent i = new Intent(Home.this, Answer.class);
				startActivity(i);
			} else if (pageno == 3) {
				Intent i = new Intent(Home.this, Iscorrect.class);
				startActivity(i);
			} else if (pageno == 4) {
				Intent i = new Intent(Home.this, Game.class);
				startActivity(i);
			}

		}

		});

		mResetButton = (Button) findViewById(R.id.reset_button);
		mResetButton.setOnClickListener(new View.OnClickListener() {@Override
																	public void onClick(View v) {

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					context);
			// set title
			alertDialogBuilder.setTitle("Warning!");


			// set dialog message
			alertDialogBuilder.setMessage("You will lose all your hints and progress if you reset the game. Are you sure you want to " + "continue?")
					.setCancelable(false)
					.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {

							editor.putInt("SoundNumber", 0);
							editor.commit();

							changer.putInt("PageNumber", 0);
							changer.commit();

							hintchanger.putInt("hints", 10);
							hintchanger.commit();

							counterchanger.putInt("correctno", 0);
							counterchanger.commit();

						}
					})
					.setNegativeButton("No", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});

			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();
		}

		});


		mShareButton = (ImageButton) findViewById(R.id.share_button);
		mShareButton.setOnClickListener(new View.OnClickListener() {@Override
																	public void onClick(View v) {
			Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
			sharingIntent.setType("text/plain");
			String shareBody = "Check out this fun trivia game! http://goo.gl/hRaJO1";
			sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Sound Logo Quiz");
			sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
			startActivity(Intent.createChooser(sharingIntent, "Share via"));
		}

		});


	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//Handle the back button
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			//Ask the user if they want to quit
			new AlertDialog.Builder(this)
					.setIcon(R.drawable.ic_dialog_alert)
					.setTitle(R.string.quit)
					.setMessage(R.string.really_quit)
					.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

							//Stop the activity
							Home.this.finish();
						}

					})
					.setNegativeButton(R.string.no, null)
					.show();

			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}

	}@Override
	 protected void onPause() {
		super.onPause();

		SharedPreferences pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		SharedPreferences pagenumber = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
		SharedPreferences.Editor changer = pagenumber.edit();
		SharedPreferences hints = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
		SharedPreferences.Editor hintchanger = hints.edit();

		hintchanger.putInt("hints", hints.getInt("hints", 10));
		hintchanger.commit();

		editor.putInt("SoundNumber", pref.getInt("SoundNumber", 0));
		editor.commit();


		changer.putInt("PageNumber", pagenumber.getInt("PageNumber", 0));
		changer.commit();

		SharedPreferences correctcounter = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
		SharedPreferences.Editor counterchanger = correctcounter.edit();
		counterchanger.putInt("correctno", correctcounter.getInt("correctno", 0));
		counterchanger.commit();

	}
}