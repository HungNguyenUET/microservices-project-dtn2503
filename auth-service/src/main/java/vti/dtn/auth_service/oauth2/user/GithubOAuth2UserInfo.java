package vti.dtn.auth_service.oauth2.user;

import java.util.Map;

public class GithubOAuth2UserInfo extends OAuth2UserInfo {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String AVATAR_URL = "avatar_url";
    private static final String LOGIN = "login";

    protected GithubOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return String.valueOf( attributes.get(ID));
    }

    @Override
    public String getName() {
        return attributes.get(NAME) != null ? String.valueOf(attributes.get(NAME)) : String.valueOf(attributes.get(LOGIN));
    }

    @Override
    public String getEmail() {
        return String.valueOf(attributes.get(EMAIL));
    }

    @Override
    public String getImageUrl() {
        return String.valueOf(attributes.get(AVATAR_URL));
    }
}
