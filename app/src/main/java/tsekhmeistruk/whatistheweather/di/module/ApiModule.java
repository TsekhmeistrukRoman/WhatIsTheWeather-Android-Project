package tsekhmeistruk.whatistheweather.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import tsekhmeistruk.whatistheweather.Constants;

/**
 * Created by Roman Tsekhmeistruk on 28.03.2017.
 */

@Module
public class ApiModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        JsonDeserializer<String> deserializer = (json, typeOfT, context) -> {
            JsonObject jsonObject = json.getAsJsonObject();
            return jsonObject.get("").getAsString();
        };

        gsonBuilder.registerTypeAdapter(Date.class, deserializer);
        Gson customGson = gsonBuilder.create();

        return new Retrofit.Builder()
                .baseUrl(Constants.GOOGLE_MAP_API)
                .addConverterFactory(GsonConverterFactory.create(customGson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

}
