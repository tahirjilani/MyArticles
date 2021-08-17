# MyArticles
This application shows articles list and upon clicking an item it opens article details

Design patterns:
MVVM
Hilt for Dependency Injection

Architecture Pattern:
Clean Architecture

Coroutines
LiveData
Retrofit2

Testing:
JUnit
Espresso


How to run this repository?
1. Clone this Git repository
2. Run Android Studio and click Open and select repository folder. I used latest version Android Studio Arctic Fox 2020-3-1
3. It will automatically perform gradle build
4. Attach a device or select your AVD and hit run button on toolbar

How to test?
1. In project pane, select Android
2. Expand java and select com(androidTest), right click and choose Run 'Tests in 'com''
3. It will run all the UI tests on your selected device/emulator

4. For Unit tests, select com(test), right click, More Run/Debug -> Run 'Tests in 'com'' with Coverage
5. After it finishes, open Coverage section and click on Generate Coverage Report icon
6. Select report destination to save code coverage report

Note: due to lack of time we only tried to cover necessary code in tests.