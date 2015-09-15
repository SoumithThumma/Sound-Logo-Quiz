package dropoutinnovations.soundlogoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Game extends Activity {
	private Button mOkButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_page);

		mOkButton = (Button) findViewById(R.id.ok_button);
		mOkButton.setOnClickListener(new View.OnClickListener() {@Override
																 public void onClick(View v) {
			Game.this.finish();
		}
		});
	}

}