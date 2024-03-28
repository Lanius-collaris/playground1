**Build aar**
```
cd golang/
gomobile bind -target android -androidapi 21 -o abc.aar -trimpath ./abc
mkdir -p ../app/libs
cp abc.aar ../app/libs
```