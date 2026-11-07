# আমার আঞ্চলিক — Android Keyboard MVP

প্রথম কার্যকর সংস্করণ: Android Input Method Service (IME) ভিত্তিক বাংলা/ইংরেজি কিবোর্ড।

## এই সংস্করণে আছে
- Android system keyboard service
- বাংলা অক্ষর টাইপিং
- ইংরেজি QWERTY টাইপিং
- বাংলা/English মোড পরিবর্তন
- স্পেস, ব্যাকস্পেস, এন্টার
- ইমোজি বোতামের প্রাথমিক সুবিধা
- কোনো নেটওয়ার্ক পারমিশন নেই; টাইপ করা লেখা কোথাও পাঠানো হয় না

## এখনও নেই
- ৬৪ জেলার যাচাইকৃত আঞ্চলিক শব্দভান্ডার
- শব্দ সাজেশন ইঞ্জিন
- প্রকৃত ভয়েস-টু-টেক্সট
- স্টিকার প্যাক
- পূর্ণ সংখ্যা/চিহ্ন প্যানেল
- Play Store release signing

## APK তৈরি (GitHub browser workflow)
1. GitHub-এ নতুন repository তৈরি করুন, নাম `amar-ancholik`।
2. এই ZIP-এর ভেতরের ফাইলগুলো repository-র root-এ upload করুন।
3. Commit করুন `main` branch-এ।
4. GitHub repository → Actions → `Build Android APK` → Run workflow।
5. Build শেষ হলে workflow run-এর Artifacts থেকে `amar-ancholik-debug-apk` ডাউনলোড করুন।
6. ZIP extract করে `app-debug.apk` Android ফোনে ইনস্টল করুন।

## ফোনে কিবোর্ড চালু
1. অ্যাপ খুলুন।
2. “কিবোর্ড চালু করুন” চাপুন।
3. Android Settings-এ “আমার আঞ্চলিক কিবোর্ড” চালু করুন।
4. অ্যাপে ফিরে “কিবোর্ড নির্বাচন করুন” চাপুন।
5. Input method picker থেকে “আমার আঞ্চলিক” নির্বাচন করুন।

## গুরুত্বপূর্ণ
এটি একটি starter MVP; ফোন ও Android সংস্করণভেদে পরীক্ষা করতে হবে। কিবোর্ডকে ডিফল্ট করার আগে পরিচিত/বিশ্বস্ত APK-ই ইনস্টল করুন। কোনো পাসওয়ার্ড/OTP বা ব্যক্তিগত লেখা AI-তে পাঠানোর ব্যবস্থা এই সংস্করণে নেই।
