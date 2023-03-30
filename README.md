# Futbol Lig Puan Durumu
Bu proje Bilgisayar Mühendisliği Mobil Programlama ödevi olarak geliştirilmiştir. Bu projede, seçilen futbol ligine göre puan durumunu listeleyen bir uygulama geliştirilmiştir. Uygulama, verileri dinamik olarak ApiFootbol aracılığıyla güncellemektedir. HTTP istekleri için Retrofit kütüphanesi kullanılmıştır. Uygulama içerisinde API' dan alınan fotoğrafların yüklenmesi için Glide kütüphanesi kullanılmıştır.

## Kullanılan Teknolojiler
- Java
- [Retrofit](https://square.github.io/retrofit)
- [Glide](https://github.com/bumptech/glide)

## API Key Oluşturma
Projeyi kullanmak isterseniz [ApiFootball](https://apifootball.com/register/)' a kayıt olarak kendinize ait ücretsiz bir API KEY oluşturabilirsiniz.
Oluşturduğunuz API KEY' i projedeki leagueApi paketi altına aşağıdaki Enum class' ı ekleyerek kullanabilirsiniz.

```java
public enum ApiKey {
    API_KEY;
    private final String key = "your_api_key";
    public String getKey(){
        return key;
    }
}
```

## Ekran Görüntüleri
![image](https://user-images.githubusercontent.com/66082185/228779356-23a8ea4c-5067-45dd-80b7-f73d20c32ac8.png)
![image](https://user-images.githubusercontent.com/66082185/228780409-a6723cc6-7f6d-4d53-8ab7-1b76b25a07ea.png)
![image](https://user-images.githubusercontent.com/66082185/228779907-9153f520-a72d-4486-85ca-3496687230ca.png)

