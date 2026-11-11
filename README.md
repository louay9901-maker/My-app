# وش نسوي؟ 🎲

تطبيق Android ترفيهي ذكي يساعدك على التخلص من الملل بأفكار وأنشطة عشوائية.

## المميزات
- 6 أقسام: ألعاب، أفلام، معلومات غريبة، تحديات، تعلّم، مفاجآت
- أكثر من 90 اقتراحاً متنوعاً مع منطق منع التكرار
- المفضلة محفوظة محلياً على الجهاز (بدون خادم أو تسجيل دخول)
- وضع ليلي/نهاري مع حفظ التفضيل
- واجهة عربية كاملة RTL، تصميم Material 3 هادئ، حركات انتقالية سلسة

## التقنيات
- Kotlin + Jetpack Compose (Material 3)
- بدون Room/قاعدة بيانات خارجية — التخزين عبر SharedPreferences (JSON)
- minSdk 24 | targetSdk 34

## طريقة بناء ملف APK
### الطريقة 1: Android Studio (الأسهل)
1. افتح مجلد المشروع في Android Studio.
2. انتظر انتهاء Sync (ستُحمَّل الاعتماديات تلقائياً).
3. من القائمة: Build > Build Bundle(s) / APK(s) > Build APK(s).
4. ستجد الملف في: app/build/outputs/apk/debug/app-debug.apk
5. انسخه لهاتفك وثبّته مباشرة.

### الطريقة 2: سطر الأوامر
1. ثبّت JDK 17.
2. أنشئ Gradle Wrapper (مرة واحدة): أنشئ مشروعاً جديداً في Android Studio ثم انسخ مجلد gradle/wrapper + ملفات gradlew* إلى هنا، أو نفّذ:
   gradle wrapper --gradle-version 8.7
3. نفّذ:
   - على Linux/Mac: ./gradlew assembleDebug
   - على Windows: gradlew.bat assembleDebug
4. الملف الناتج: app/build/outputs/apk/debug/app-debug.apk

### بناء نسخة Release (موقّعة)
افتح Build > Generate Signed Bundle / APK في Android Studio وأنشئ مفتاح توقيع جديد.

## بنية المشروع
- app/src/main/java/com/wansawi/app/
  - data/ → المحتوى، المفضلة، الإعدادات
  - ui/ → الموديل + الشاشات
  - ui/theme/ → الألوان والثيم
  - MainActivity.kt → نقطة الدخول والتنقل
