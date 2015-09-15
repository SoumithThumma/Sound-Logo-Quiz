package dropoutinnovations.soundlogoquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Iscorrect extends Activity {
	private TextView answerText;
	private Button nextButton;
	private TextView LevelNo;
	private TextView newHint;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.iscorrect_page);

		final SharedPreferences pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
		final SharedPreferences.Editor editor = pref.edit();
		final SharedPreferences pagenumber = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
		final SharedPreferences.Editor changer = pagenumber.edit();
		final SharedPreferences correctcounter = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
		final SharedPreferences hints = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
		final SharedPreferences.Editor hintchanger = hints.edit();

		int correctno = correctcounter.getInt("correctno", 0);
		if ((correctno % 2) == 0) {
			int hintsleft = hints.getInt("hints", 10);
			hintsleft++;
			hintchanger.putInt("hints", hintsleft);
			hintchanger.commit();

			newHint = (TextView) findViewById(R.id.new_hint);
			newHint.setText("You get a new hint!");
		}

		LevelNo = (TextView) findViewById(R.id.level_no);
		int i = pref.getInt("SoundNumber", 0);
		String levelno = String.valueOf(i + 1);
		LevelNo.setText(levelno);

		nextButton = (Button) findViewById(R.id.next_button);
		nextButton.setOnClickListener(new View.OnClickListener() {@Override
																  public void onClick(View v) {
			int current = pref.getInt("SoundNumber", 0);
			if (current == 47) {
				Iscorrect.this.finish();
				Intent i = new Intent(Iscorrect.this, Game.class);
				startActivity(i);
				changer.putInt("PageNumber", 4);
				changer.commit();
			} else {
				int later = current + 1;
				editor.putInt("SoundNumber", later);
				editor.commit();
				Iscorrect.this.finish();
				Intent i = new Intent(Iscorrect.this, Levels.class);
				startActivity(i);
				changer.putInt("PageNumber", 0);
				changer.commit();
			}

		}

		});

		answerText = (TextView) findViewById(R.id.correct_answer);
		answerText.setText((Sound.answerArray[pref.getInt("SoundNumber", 0)]).toUpperCase());


	}@Override
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