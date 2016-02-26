package org.jenkinsci.plugins.ParameterizedRemoteTrigger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class RemoteBuildConfigurationMethodTest {

    private static final String SERVER_NAME = "JENKINS";

    private static final String KEY_ACTIONS = "actions";

    @Test(expected = JSONException.class)
    public void testResponseActionsWrongFormat() throws Exception {
        final RemoteBuildConfiguration remoteBuildConfiguration = createRemoteBuildConfiguration();
        final JSONObject response = new JSONObject();
        response.put(KEY_ACTIONS, "text");

        remoteBuildConfiguration.hasResponseActions(response);
    }

    @Test
    public void testResponseActionsEmpty() throws Exception {
        final RemoteBuildConfiguration remoteBuildConfiguration = createRemoteBuildConfiguration();
        final JSONObject response = new JSONObject();
        final JSONArray jsonArray = new JSONArray();
        jsonArray.add(new JSONObject());
        jsonArray.add(new JSONObject());
        response.put(KEY_ACTIONS, jsonArray);

        assertFalse(remoteBuildConfiguration.hasResponseActions(response));
    }

    @Test
    public void testResponseActionsHasValue() throws Exception {
        final RemoteBuildConfiguration remoteBuildConfiguration = createRemoteBuildConfiguration();
        final JSONObject response = new JSONObject();
        final JSONObject obj2 = new JSONObject();
        obj2.put(KEY_ACTIONS, SERVER_NAME);
        final JSONArray jsonArray = new JSONArray();
        jsonArray.add(new JSONObject());
        jsonArray.add(obj2);
        response.put(KEY_ACTIONS, jsonArray);

        assertTrue(remoteBuildConfiguration.hasResponseActions(response));
    }

    private RemoteBuildConfiguration createRemoteBuildConfiguration() throws Exception {
        return new RemoteBuildConfiguration(SERVER_NAME, false, "FullName", "", "", true, null, null, false, true, 1);
    }
}
