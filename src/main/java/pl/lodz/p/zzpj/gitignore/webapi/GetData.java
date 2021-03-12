package pl.lodz.p.zzpj.gitignore.webapi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import pl.lodz.p.zzpj.gitignore.webapi.exception.WebApiException;
import pl.lodz.p.zzpj.gitignore.webapi.model.TechnologyObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

public class GetData implements GetDataInterface {

    private final String API_URL = "https://www.toptal.com/developers/gitignore/api/list?format=json";

    private HashMap<String, TechnologyObject> technologyMap = null;

    public GetData() throws WebApiException {
        try {
            String technologyList = readUrl(API_URL);
            Gson gson = new Gson();
            technologyMap = gson.fromJson(technologyList, new TypeToken<HashMap<String, TechnologyObject>>(){}.getType());
        } catch (Exception e) {
            throw new WebApiException(e);
        }
    }

    private String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder buffer = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    @Override
    public HashMap<String, TechnologyObject> getTechnologyMap() {
        return technologyMap;
    }
}
