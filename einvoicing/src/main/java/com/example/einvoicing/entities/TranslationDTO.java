package com.example.einvoicing.entities;

public class TranslationDTO {
        public int status;
        public String from;
        public String to;
        public String original_text;
        public TranslatedText translated_text;
        public int translated_characters;


    public class TranslatedText{
        public String ar;
    }

}
