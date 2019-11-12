package com.xDish.PaperTrail.Services;

public class ApiUrlBuilder {

    private String urlPicker(int variant) {
        String urlFragment;
        switch (variant) {
            case 1:
                urlFragment = "https://www.goodreads.com/search/index.xml?key=v8zzKlJwTVg7h9uJXCuzpg&search[field]=author&q=";
                break;
            default:
                urlFragment = "https://www.goodreads.com/search/index.xml?key=v8zzKlJwTVg7h9uJXCuzpg&search[field]=author&q=";
                break;
        }
        return urlFragment;
    }

    public String buildUrl(int urlVariant, String search){
        return urlPicker(urlVariant)+ search;
    }
}