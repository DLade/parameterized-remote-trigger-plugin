package org.jenkinsci.plugins.ParameterizedRemoteTrigger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class RemoteBuildConfigurationMethodTest {

    private static final String SERVER_NAME = "JENKINS";

    private static final String KEY_ACTIONS = "actions";

    private RemoteBuildConfiguration remoteBuildConfiguration;

    @Before
    public void createRemoteBuildConfiguration() throws Exception {
        remoteBuildConfiguration = new RemoteBuildConfiguration(SERVER_NAME, false, "FullName", "",
                "", true, null, null, false, true, 1);
    }

    @Test(expected = JSONException.class)
    public void testResponseActionsWrongFormat() throws Exception {
        final JSONArray actions = new JSONArray();
        actions.add(new JSONArray());

        remoteBuildConfiguration.hasResponseActions(actions);
    }

    @Test
    public void testResponseActionsWithEmptyArray() throws Exception {
        final JSONArray actions = new JSONArray();

        assertFalse(remoteBuildConfiguration.hasResponseActions(actions));
    }

    @Test
    public void testResponseActionsWithEmptyObjects() throws Exception {
        final JSONArray actions = new JSONArray();
        actions.add(new JSONObject());
        actions.add(new JSONObject());

        assertFalse(remoteBuildConfiguration.hasResponseActions(actions));
    }

    @Test
    public void testResponseActionsHasValue() throws Exception {
        final JSONObject obj2 = new JSONObject();
        obj2.put(KEY_ACTIONS, SERVER_NAME);

        final JSONArray actions = new JSONArray();
        actions.add(new JSONObject());
        actions.add(obj2);

        assertTrue(remoteBuildConfiguration.hasResponseActions(actions));
    }
}
