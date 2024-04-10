# プロジェクトをデプロイして実行する

## 動作環境

- OS: Ubuntu 22.04

- JDK: OpenJDK 17

- Tomcat: 9

- Database: H2 Database Engine

## デプロイプロセス

### 1. JDK17をインストールする

Ubuntu で次の手順を実行します。

```shell
sudo apt update 
sudo apt install openjdk-17-jdk
sudo apt install openjdk-17-jre
```

インストールが成功したかどうかを確認します。

```shell
java --version
```

![](assets_deploy-and-run-the-project/2024-04-10-17-09-06-image.png)

**その他のケース**

デフォルトの Java バージョンの設定は、複数の Java バージョンがシステムにインストールされている状況に適しています。 まず、利用可能なバージョンをすべてリストします。

```shell
sudo update-alternatives --config java
```

数値を入力して、デフォルトのバージョンとして設定する Java バージョンを選択します。

### 2. H2 Databaseをインストールする

H2 Databaseの圧縮パッケージは以下のgithubにあります。

[accounting-web-app/database/h2.7z at main · chenxingxu3/accounting-web-app · GitHub](https://github.com/chenxingxu3/accounting-web-app/blob/main/database/h2.7z)

![](assets_deploy-and-run-the-project/2024-04-10-16-46-40-image.png)

7z アーカイブを解凍します。

![](assets_deploy-and-run-the-project/2024-04-10-17-16-33-image.png)

解凍したフォルダーのh2をターミナルに入力します。

例えば

```shell
cd /home/YourUserName/Document/accounting-web-app/h2
```

h2.sh に実行権限を付与します。

![](assets_deploy-and-run-the-project/2024-04-10-17-23-53-image.png)

![](assets_deploy-and-run-the-project/2024-04-10-17-24-17-image.png)

実行するとでてくるFailed to load module "canberra-gtk-module"というエラーの対処法

これはズバリターミナルで以下のように実行し

```shell
sudo apt-get install libcanberra-gtk*
```

h2.sh を実行します

```shell
sudo ./h2.sh
```

アクセス

http://localhost:8082/

![](assets_deploy-and-run-the-project/2024-04-10-17-32-12-image.png)

![](assets_deploy-and-run-the-project/2024-04-10-17-33-13-image.png)

![](assets_deploy-and-run-the-project/2024-04-10-17-33-54-image.png)

### 3. Tomcat9をインストールする

Tomcat 9 をダウンロードします。

[Apache Tomcat - Apache Tomcat 9 Software Downloads](https://tomcat.apache.org/download-90.cgi)

![](assets_deploy-and-run-the-project/2024-04-10-17-36-45-image.png)

tar.gzアーカイブを解凍します。

![](assets_deploy-and-run-the-project/2024-04-10-17-38-09-image.png)

解凍したフォルダーに入り、webappsフォルダー配下のファイルをすべて削除します。

![](assets_deploy-and-run-the-project/2024-04-10-17-39-41-image.png)

![](assets_deploy-and-run-the-project/2024-04-10-17-40-03-image.png)

![](assets_deploy-and-run-the-project/2024-04-10-17-40-41-image.png)

### 4. プロジェクトを実行する

パッケージ化された war パッケージを GitHub からダウンロードします。

[Release war file · chenxingxu3/accounting-web-app · GitHub](https://github.com/chenxingxu3/accounting-web-app/releases/tag/v0.0.1)

![](assets_deploy-and-run-the-project/2024-04-10-16-51-12-image.png)

kaikei.war ファイルを webapps フォルダーにコピーします。

![](assets_deploy-and-run-the-project/2024-04-10-17-42-30-image.png)

apache-tomcat-9.0.XX フォルダーに戻ります。

![](assets_deploy-and-run-the-project/2024-04-10-17-44-07-image.png)

bin ディレクトリを入力します。

前の手順を参照して、startup.sh に実行権限を与えます。

![](assets_deploy-and-run-the-project/2024-04-10-17-51-21-image.png)

![](assets_deploy-and-run-the-project/2024-04-10-17-51-50-image.png)

binディレクトリでターミナルを実行します。

![](assets_deploy-and-run-the-project/2024-04-10-17-48-07-image.png)

次のコマンドを実行します。

```shell
sudo ./startup.sh
```

![](assets_deploy-and-run-the-project/2024-04-10-17-53-17-image.png)

「Tomcat started」が表示されれば起動成功です。

以下のURLにアクセスしてください

http://localhost:8080/kaikei/userlogin

![](assets_deploy-and-run-the-project/2024-04-10-18-00-44-image.png)
