package com.example.news_app_2;

import java.util.List;

public class MultiResource {
    private int totalArticles;
    private List<Articles> articles;

    public class Articles{


        private String title;
        private String description;
        private String content;
        private String url;
        private String image;
        private String publishedAt;
        private Source source;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public Source getSource() {
            return source;
        }

        public void setSource(Source source) {
            this.source = source;
        }

        @Override
        public String toString() {
            return "Articles{" +
                    "title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", content='" + content + '\'' +
                    ", url='" + url + '\'' +
                    ", image='" + image + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", source=" + source +
                    '}';
        }
    }

    public int getTotalArticles() {
        return totalArticles;
    }

    public void setTotalArticles(int totalArticles) {
        this.totalArticles = totalArticles;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "MultiResource{" +
                "totalArticles=" + totalArticles +
                ", articles=" + articles +
                '}';
    }
}
