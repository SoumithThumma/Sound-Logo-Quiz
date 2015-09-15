package dropoutinnovations.soundlogoquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Answer extends Activity {
	private ImageButton mPlaySoundButton;
	private TextView answerText;
	private Button nextButton;
	private TextView LevelNo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.answer_page);

		final SharedPreferences pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
		final SharedPreferences.Editor editor = pref.edit();
		final SharedPreferences pagenumber = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
		final SharedPreferences.Editor changer = pagenumber.edit();

		LevelNo = (TextView) findViewById(R.id.level_no);
		int i = pref.getInt("SoundNumber", 0);
		String levelno = String.valueOf(i + 1);
		LevelNo.setText(levelno);

		answerText = (TextView) findViewById(R.id.correct_answer);
		answerText.setText((Sound.answerArray[pref.getInt("SoundNumber", 0)]).toUpperCase());

		mPlaySoundButton = (ImageButton) findViewById(R.id.playsound_button);
		final MediaPlayer mpanswer = MediaPlayer.create(this, Sound.soundArray[pref.getInt("SoundNumber", 0)]);
		mPlaySoundButton.setOnClickListener(new View.OnClickListener() {@Override
																		public void onClick(View v) {

			mpanswer.start();
		}
		});

		nextButton = (Button) findViewById(R.id.next_button);
		nextButton.setOnClickListener(new View.OnClickListener() {@Override
																  public void onClick(View v) {
			int current = pref.getInt("SoundNumber", 0);
			if (current == 47) {
				Answer.this.finish();
				Intent i = new Intent(Answer.this, Game.class);
				startActivity(i);
				changer.putInt("PageNumber", 4);
				changer.commit();
			} else {
				int later = current + 1;
				editor.putInt("SoundNumber", later);
				editor.commit();
				mpanswer.stop();
				Answer.this.finish();
				Intent i = new Intent(Answer.this, Levels.class);
				startActivity(i);
				changer.putInt("PageNumber", 0);
				changer.commit();
			}
		}

		});

	}

	@Override
	protected void onPause() {
		super.onPause();

		SharedPreferences hints = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
		SharedPreferences.Editor hintchanger = hints.edit();

		hintchanger.putInt("hints", hints.getInt("hints", 10));
		hintchanger.commit();

		SharedPreferences pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.putInt("SoundNumber", pref.getInt("SoundNumber", 0));
		editor.commit();

		SharedPreferences pagenumber = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
		SharedPreferences.Editor changer = pagenumber.edit();
		changer.putInt("PageNumber", pagenumber.getInt("PageNumber", 0));
		changer.commit();

		SharedPreferences correctcounter = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
		SharedPreferences.Editor counterchanger = correctcounter.edit();
		counterchanger.putInt("correctno", correctcounter.getInt("correctno", 0));
		counterchanger.commit();
	}
}