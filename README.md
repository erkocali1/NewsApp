# 📰 NewsApp
✓ NewsApp with Firebase and Dagger Hilt &MVVM( Data-Domain- UI Layer )
 <img src="https://github.com/erkocali1/NewsApp/blob/master/app/src/main/res/drawable/ss1.jpg" alt="Resim">
  </head>
<body>
  <h1>🖊️Uygulamaya Ön Bakış</h1>
  <p>
Kotlin programlama dilini kullanarak geliştirdiğim bu uygulama, haberlere erişim, haber arama ve haber kaydetme  konusunda size  bir deneyim sunuyor. Clean Architecture, Firebase, Dagger Hilt, Room, Retrofit, RecyclerView gibi güçlü teknolojileri kullanarak oluşturduğum bu uygulama,güncel teklonjilere uygun olarak yapılmıştır.

Uygulamanın ana mantığı, bir haber API'si ile iletişim kurarak güncel haberleri çekmek, bu haberleri yerel bir veritabanına kaydetmek ve daha sonra kullanıcıya göstermek. Kullanıcılar, aradıkları haberleri kolayca bulabilir, ilgi çeken haberleri kaydedebilir ve takip edebilirler.

Uygulama, kullanıcı dostu bir arayüze sahip ve temiz bir kod yapısıyla oluşturuldu. ViewModel'lar ve state yönetimi kullanılarak, kullanıcı etkileşimlerini sorunsuz bir şekilde yönetir ve kullanıcıları her adımda bilgilendirir.

Haberleri bulmak ve takip etmek artık daha kolay. Bu uygulama ile güncel olaylardan haberdar olabilir ve ilginç haberleri kaydedebilirsiniz. Kotlin ile geliştirilen bu haber uygulaması, size haberleri takip etme konusunda en iyi deneyimi sunar.
 </p>
 <h2>📝 Kullanılan Componentler</h2>
<ul>
  <li>Pagination</li>
  <li>Dagger Hilt</li>
  <li>FireBase</li>
  <li>MVVM (Data-Domain-UI Layer)</li>
  <li>WebView</li>
  <li>Retrofit</li>
  <li>Room</li>
  <li>Navigation Component</li>
  <li>Splash Screen API</li>
  <li>RecyclerView</li>
  <li>Coroutine</li>
  <li>Flow</li>
  <li>LifeCycle</li>
  <li>Glide</li>
</ul>
 <h2>📱 Sayfalar</h2>
   </p>
 <img src="https://github.com/erkocali1/NewsApp/blob/master/app/src/main/res/drawable/ss2.png" alt="Resim">
   </p>
  <img src="https://github.com/erkocali1/NewsApp/blob/master/app/src/main/res/drawable/ss3.png" alt="Resim">

  <h2>📝Dagger Hilt Nedir </h2>
Dagger Hilt, bağımlılık enjeksiyonu çerçevesi olarak kullanılan bir Android Jetpack kütüphanesidir. Dagger Hilt, Android uygulamalarındaki bağımlılıkları (örneğin, veritabanı, ağ isteği, servisler) yönetmek ve enjekte etmek için kullanılır. Bu sayede uygulamalar daha sürdürülebilir, test edilebilir ve bakımı daha kolay hale gelir.

Yukarıda yaptığım uygulamamda Dagger Hilt'in görevi, bağımlılıkları enjekte etmek ve yönetmek için kullanılmıştır. Özellikle Dependency Injection (Bağımlılık Enjeksiyonu) konseptini uygularken Dagger Hilt kullanılır. Bu, iş mantığını ve veri kaynağını ayırmak, bileşenler ve modüller aracılığıyla bağımlılıkları bildirmek ve bunları kullanıcı etkileşimiyle bağlamak için kullanılır.

Kısacası, Dagger Hilt, bağımlılık enjeksiyonu ile ilgili Android uygulamalarınızda kullanabileceğiniz güçlü bir araçtır ve yukarıda açıkladığm haber uygulamamızdaki görevi, uygulamamızın temel bağımlılıklarını yönetmek ve enjekte etmek için kullanılmasıdır.
![Ekran görüntüsü 2023-10-23 140255](https://github.com/erkocali1/NewsApp/assets/116030125/2e02418b-2d10-4964-835f-c4492fc77758)



https://github.com/erkocali1/NewsApp/assets/116030125/cdf45ec7-389a-4fea-bb2e-a29d77becce6


## 📝: Dependency
```dependencies {
  // Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    // http client
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    //interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // Navigation Components
    implementation "androidx.navigation:navigation-fragment-ktx:2.5.3"
    implementation "androidx.navigation:navigation-ui-ktx:2.5.3"

    // Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4'

    // Coroutine Lifecycle Scopes
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.6.1"

    //room
    implementation 'androidx.room:room-runtime:2.5.2'
    kapt 'androidx.room:room-compiler:2.5.2'
    annotationProcessor "androidx.room:room-compiler:2.5.2"
    implementation "androidx.room:room-ktx:2.5.2"

    //BottomBar
    implementation 'com.github.ismaeldivita:chip-navigation-bar:1.4.0'

    //Coil
    implementation 'io.coil-kt:coil:1.3.2'

    //pagination
    def paging_version = "3.1.1"
    implementation "androidx.paging:paging-runtime:$paging_version"

    //firabase
    implementation(platform"com.google.firebase:firebase-bom:32.3.1")
    implementation"com.google.firebase:firebase-analytics-ktx"

    implementation"com.google.firebase:firebase-auth-ktx"
    implementation"com.google.android.gms:play-services-auth:20.7.0"

    //Splash Screen
    implementation "androidx.core:core-splashscreen:1.0.1"



}
```
  

