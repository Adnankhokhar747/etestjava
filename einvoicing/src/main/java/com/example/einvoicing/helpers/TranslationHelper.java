package com.example.einvoicing.helpers;

import com.example.einvoicing.entities.TranslationDTO;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TranslationHelper {

    public static String translate(String text) {
        String translatedText = "";
        try {
            Gson gson = new Gson();
            String RAPID_API_KEY = "0b91381b0emshf4af0963672c2efp1416dfjsn986d02f17c68";
            String NLP_HOST = "nlp-translation.p.rapidapi.com";
            text = text.replace(" ", "/");
            String url = "https://nlp-translation.p.rapidapi.com/v1/translate?text=" + text + "&to=ar&from=en";
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            HttpClient httpClient = HttpClient.newBuilder().executor(executorService).version(HttpClient.Version.HTTP_2).build();
            HttpResponse<String> response = httpClient.send(HttpRequest.newBuilder().GET().uri(URI.create(url))
                    .setHeader("Accept", "Accept")
                    .setHeader("X-RapidAPI-Key", RAPID_API_KEY)
                    .setHeader("X-RapidAPI-Host", NLP_HOST)
                    .build(), HttpResponse.BodyHandlers.ofString());
            TranslationDTO translationDTO = gson.fromJson(response.body(), TranslationDTO.class);
            TranslationDTO.TranslatedText translatedTextClass = translationDTO.translated_text;
            translatedText = translatedTextClass.ar;
            translatedText = translatedText.replace("/", " ");
        } catch (Exception ex) {
            System.out.println("error in translation:" + ex.getMessage());
            return ex.getMessage();
        }
        return translatedText;
    }

    public static String getTranslation(String english)  {
        if(english == null)
            return "";
        else {
            String arabic = null;
            try {
                //  arabic = translate("en", "ar", english);
                arabic = translate(english);
                System.out.println(arabic);
            } catch (Exception ex) {
                arabic = "Error in translation: " + ex.getMessage();
            }
            return arabic;
        }
    }

//        @Value("${google.api.key}")
//        private String apiKey;
//
//        private final Translate translate;
//
//        public TranslationHelper() throws IOException {
//            TranslateOptions options = TranslateOptions.newBuilder()
//                    .setApiKey(apiKey)
//                    .build();
//            translate = options.getService();
//        }
//
//        public String translate(String text) {
//            Translation translation = translate.translate(text, Translate.TranslateOption.targetLanguage("ar"));
//            return translation.getTranslatedText();
//        }

}

