package com.picker.server;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.mixpanel.mixpanelapi.MessageBuilder;
import com.mixpanel.mixpanelapi.MixpanelAPI;
import com.mixpanel.mixpanelapi.MixpanelMessageException;

public class MixpanelController {
	private String PROJECT_TOKEN = "60880e39143e4f65535e9e2c57f41744";
	
	MessageBuilder messageBuilder =
		    new MessageBuilder(PROJECT_TOKEN);
	
	private void trackUserLogin(String userID, String username) throws JSONException, MixpanelMessageException, IOException {
		JSONObject props = new JSONObject();
		props.put(userID, username);
		JSONObject update = messageBuilder.set("userID", props);

		// Send the update to mixpanel
		MixpanelAPI mixpanel = new MixpanelAPI();
		mixpanel.sendMessage(update);
	}
}
//API KEY 60880e39143e4f65535e9e2c57f41744