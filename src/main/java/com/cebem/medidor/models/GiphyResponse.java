package com.cebem.medidor.models;

import lombok.Data;
import java.util.List;

@Data
public class GiphyResponse {
    private List<Gif> data;
    
    @Data
    public static class Gif {
        private String id;
        private String title;
        private Images images;
        
        @Data
        public static class Images {
            private Original original;
            private FixedHeight fixed_height;
            
            @Data
            public static class Original {
                private String url;
                private String width;
                private String height;
                private String size;
            }
            
            @Data
            public static class FixedHeight {
                private String url;
                private String width;
                private String height;
                private String size;
            }
        }
    }
}