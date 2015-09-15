package dropoutinnovations.soundlogoquiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;


public class Levels extends Activity {
	private Button mHintButton;
	private Button mAnswerButton;
	private Button mPlaySoundButton;
	private Button mCheckButton;
	final Context context = this;
	private EditText mUserAnswer;
	public String answer = "";
	private TextView LevelNo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.levels_page);

		final SharedPreferences pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
		SharedPreferences pagenumber = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
		final SharedPreferences.Editor changer = pagenumber.edit();
		final SharedPreferences hints = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
		final SharedPreferences.Editor hintchanger = hints.edit();
		final SharedPreferences correctcounter = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
		final SharedPreferences.Editor counterchanger = correctcounter.edit();


		LevelNo = (TextView) findViewById(R.id.level_no);
		int i = pref.getInt("SoundNumber", 0);
		String levelno = String.valueOf(i + 1);
		LevelNo.setText(levelno);

		mPlaySoundButton = (Button) findViewById(R.id.playsound_button);
		final MediaPlayer mplevels = MediaPlayer.create(this, Sound.soundArray[pref.getInt("SoundNumber", 0)]);
		mPlaySoundButton.setOnClickListener(new View.OnClickListener() {@Override
																		public void onClick(View v) {

			mplevels.start();
		}
		});

		mHintButton = (Button) findViewById(R.id.hint_button);
		mHintButton.setOnClickListener(new View.OnClickListener() {@Override
																   public void onClick(View v) {
			final int hintsleft = hints.getInt("hints", 10);

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					context);
			if (hintsleft < 1) {
				alertDialogBuilder.setMessage("No Hints Left.")
						.setCancelable(false)
						.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

								dialog.cancel();
							}
						});

			} else {
				// set title
				alertDialogBuilder.setTitle("Hint");


				// set dialog message
				alertDialogBuilder.setMessage("Cost: 1 Hint. Hints Left: " + hintsleft)
						.setCancelable(false)
						.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Levels.this.finish();
								Intent i = new Intent(Levels.this, Hint.class);
								startActivity(i);
								changer.putInt("PageNumber", 1);
								changer.commit();
								int current = hintsleft;
								int later = current - 1;
								hintchanger.putInt("hints", later);
								hintchanger.commit();
							}
						})
						.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
			}

			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();

		}
		});

		mAnswerButton = (Button) findViewById(R.id.answer_button);
		mAnswerButton.setOnClickListener(new View.OnClickListener() {@Override
																	 public void onClick(View v) {
			final int hintsleft = hints.getInt("hints", 10);

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					context);
			if (hintsleft < 2) {
				alertDialogBuilder.setMessage("You do not have enough hints.")
						.setCancelable(false)
						.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

								dialog.cancel();
							}
						});
			} else {
				// set title
				alertDialogBuilder.setTitle("Answer");

				// set dialog message
				alertDialogBuilder.setMessage("Cost: 2 Hints. Hints Left: " + hintsleft)
						.setCancelable(false)
						.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Levels.this.finish();
								Intent i = new Intent(Levels.this, Answer.class);
								startActivity(i);
								changer.putInt("PageNumber", 2);
								changer.commit();
								int current = hintsleft;
								int later = current - 2;
								hintchanger.putInt("hints", later);
								hintchanger.commit();
							}
						})
						.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
			}

			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();

		}
		});


		mUserAnswer = (EditText) findViewById(R.id.user_answer);
		mUserAnswer.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence c, int start, int before, int count) {
				answer = mUserAnswer.getText().toString();

			}

			public void beforeTextChanged(CharSequence c, int start, int count, int after) {
				// this space intentionally left blank
			}

			public void afterTextChanged(Editable c) {
				// this one too
			}
		});

		mUserAnswer.setOnEditorActionListener(new OnEditorActionListener() {
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
					if (Sound.isThisCorrect(answer, pref.getInt("SoundNumber", 0))) {
						mplevels.stop();
						Levels.this.finish();
						Intent i = new Intent(Levels.this, Iscorrect.class);
						startActivity(i);
						changer.putInt("PageNumber", 3);
						changer.commit();
						int correctno = correctcounter.getInt("correctno", 0);
						correctno++;
						counterchanger.putInt("correctno", correctno);
						counterchanger.commit();
					} else {
						Toast.makeText(getApplicationContext(),
								"Wrong Answer", Toast.LENGTH_SHORT).show();
					}
				}
				return false;
			}
		});


		mCheckButton = (Button) findViewById(R.id.check_button);
		mCheckButton.setOnClickListener(new View.OnClickListener() {@Override
																	public void onClick(View v) {
			if (Sound.isThisCorrect(answer, pref.getInt("SoundNumber", 0))) {
				mplevels.stop();
				Levels.this.finish();
				Intent i = new Intent(Levels.this, Iscorrect.class);
				startActivity(i);
				changer.putInt("PageNumber", 3);
				changer.commit();
				int correctno = correctcounter.getInt("correctno", 0);
				correctno++;
				counterchanger.putInt("correctno", correctno);
				counterchanger.commit();
			} else {
				Toast.makeText(getApplicationContext(),
						"Wrong Answer", Toast.LENGTH_SHORT).show();
			}


		}
		});
	}


	@Override
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