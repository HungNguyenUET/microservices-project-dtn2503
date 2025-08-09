package vti.dtn.auth_service.oauth2.user;

import java.util.Map;

public class FacebookOAuth2UserInfo extends OAuth2UserInfo {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PICTURE = "picture";

    protected FacebookOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return String.valueOf(attributes.get(ID));
    }

    @Override
    public String getName() {
        return String.valueOf(attributes.get(NAME));
    }

    @Override
    public String getEmail() {
        return String.valueOf(attributes.get(EMAIL));
    }

    @Override
    public String getImageUrl() {
        return String.valueOf(attributes.get(PICTURE));
    }
}
