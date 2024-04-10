<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja" data-bs-theme="auto">
  <head><script src="/kaikei/docs/5.3/assets/js/color-modes.js"></script>
  <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.7.1.js"></script>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <title>ユーザー登録</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">

<link href="/kaikei/docs/5.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        width: 100%;
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }

      .btn-bd-primary {
        --bd-violet-bg: #712cf9;
        --bd-violet-rgb: 112.520718, 44.062154, 249.437846;

        --bs-btn-font-weight: 600;
        --bs-btn-color: var(--bs-white);
        --bs-btn-bg: var(--bd-violet-bg);
        --bs-btn-border-color: var(--bd-violet-bg);
        --bs-btn-hover-color: var(--bs-white);
        --bs-btn-hover-bg: #6528e0;
        --bs-btn-hover-border-color: #6528e0;
        --bs-btn-focus-shadow-rgb: var(--bd-violet-rgb);
        --bs-btn-active-color: var(--bs-btn-hover-color);
        --bs-btn-active-bg: #5a23c8;
        --bs-btn-active-border-color: #5a23c8;
      }

      .bd-mode-toggle {
        z-index: 1500;
      }

      .bd-mode-toggle .dropdown-menu .active .bi {
        display: block !important;
      }
    </style>

    
    <!-- Custom styles for this template -->
    <link href="/kaikei/sign-in.css" rel="stylesheet">
    
    <script type="text/javascript">
        var isUsernameValid = false; // ユーザー名が有効かどうかを追跡するグローバル変数
    
	    $(document).ready(function() {
	        $('#name').blur(function() {
	            var username = $(this).val();
	            var message = $('#nameMessage');
	            
	            if (username === '') {
	                // ユーザーにユーザー名の入力を求める
	                //alert("ユーザー名を入力してください。");
	            	message.text("ユーザー名を入力してください。").css('color', 'red');
	            	isUsernameValid = false; // 無効なユーザー名
	            } else {
	                // AJAX リクエストを実行してユーザー名が存在するかどうかを確認します
	                $.ajax({
	                    url: '/kaikei/checkuser?name=' + username,
	                    type: 'GET',
	                    success: function(response) {
	                        if (response.exist) {
	                            //alert("ユーザー名は既に存在します。別のユーザー名を選んでください。");
	                        	message.text("ユーザー名は既に存在します。").css('color', 'red');
	                        	isUsernameValid = false; // 無効なユーザー名
	                        } else {
	                            //alert("ユーザー名は使用できます。");
	                        	message.text("ユーザー名は使用できます。").css('color', 'green');
	                        	isUsernameValid = true; // ユーザー名は有効です
	                        }
	                    },
	                    error: function() {
	                        //alert("エラーが発生しました。もう一度試してください。");
	                    	message.text("ユーザー名のチェックに失敗しました。").css('color', 'red');
	                    	isUsernameValid = false; // エラーが発生した場合、ユーザー名は無効とみなされます。
	                    }
	                });
	            }
	        });
	    });
    
        function validateForm() {
            var name = document.getElementById("name").value;
            var email = document.getElementById("email").value;
            var sex = document.getElementById("sex").value;
            var password = document.getElementById("password").value;
            var rpassword = document.getElementById("rpassword").value;

            var message2 = $('#message');

            if (name == "" || email == "" || sex == "性別" || password == "" || rpassword == "") {
                //alert("すべての必須項目を入力してください。");
                message2.text("すべての必須項目を入力してください。").css('color', 'red');
                return false;
            }

            if (password != rpassword) {
                //alert("パスワードが一致しません。");
                message2.text("パスワードが一致しません。").css('color', 'red');
                return false;
            }

            if (!isUsernameValid) {
                message2.text("無効なユーザー名です。").css('color', 'red');
                return false;
            }

            return true;
        }
    </script>
  </head>
  <body class="d-flex align-items-center py-4 bg-body-tertiary">
    <svg xmlns="http://www.w3.org/2000/svg" class="d-none">
      <symbol id="check2" viewBox="0 0 16 16">
        <path d="M13.854 3.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 1 1 .708-.708L6.5 10.293l6.646-6.647a.5.5 0 0 1 .708 0z"/>
      </symbol>
      <symbol id="circle-half" viewBox="0 0 16 16">
        <path d="M8 15A7 7 0 1 0 8 1v14zm0 1A8 8 0 1 1 8 0a8 8 0 0 1 0 16z"/>
      </symbol>
      <symbol id="moon-stars-fill" viewBox="0 0 16 16">
        <path d="M6 .278a.768.768 0 0 1 .08.858 7.208 7.208 0 0 0-.878 3.46c0 4.021 3.278 7.277 7.318 7.277.527 0 1.04-.055 1.533-.16a.787.787 0 0 1 .81.316.733.733 0 0 1-.031.893A8.349 8.349 0 0 1 8.344 16C3.734 16 0 12.286 0 7.71 0 4.266 2.114 1.312 5.124.06A.752.752 0 0 1 6 .278z"/>
        <path d="M10.794 3.148a.217.217 0 0 1 .412 0l.387 1.162c.173.518.579.924 1.097 1.097l1.162.387a.217.217 0 0 1 0 .412l-1.162.387a1.734 1.734 0 0 0-1.097 1.097l-.387 1.162a.217.217 0 0 1-.412 0l-.387-1.162A1.734 1.734 0 0 0 9.31 6.593l-1.162-.387a.217.217 0 0 1 0-.412l1.162-.387a1.734 1.734 0 0 0 1.097-1.097l.387-1.162zM13.863.099a.145.145 0 0 1 .274 0l.258.774c.115.346.386.617.732.732l.774.258a.145.145 0 0 1 0 .274l-.774.258a1.156 1.156 0 0 0-.732.732l-.258.774a.145.145 0 0 1-.274 0l-.258-.774a1.156 1.156 0 0 0-.732-.732l-.774-.258a.145.145 0 0 1 0-.274l.774-.258c.346-.115.617-.386.732-.732L13.863.1z"/>
      </symbol>
      <symbol id="sun-fill" viewBox="0 0 16 16">
        <path d="M8 12a4 4 0 1 0 0-8 4 4 0 0 0 0 8zM8 0a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 0zm0 13a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 13zm8-5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2a.5.5 0 0 1 .5.5zM3 8a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2A.5.5 0 0 1 3 8zm10.657-5.657a.5.5 0 0 1 0 .707l-1.414 1.415a.5.5 0 1 1-.707-.708l1.414-1.414a.5.5 0 0 1 .707 0zm-9.193 9.193a.5.5 0 0 1 0 .707L3.05 13.657a.5.5 0 0 1-.707-.707l1.414-1.414a.5.5 0 0 1 .707 0zm9.193 2.121a.5.5 0 0 1-.707 0l-1.414-1.414a.5.5 0 0 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .707zM4.464 4.465a.5.5 0 0 1-.707 0L2.343 3.05a.5.5 0 1 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .708z"/>
      </symbol>
    </svg>

    <div class="dropdown position-fixed bottom-0 end-0 mb-3 me-3 bd-mode-toggle">
      <button class="btn btn-bd-primary py-2 dropdown-toggle d-flex align-items-center"
              id="bd-theme"
              type="button"
              aria-expanded="false"
              data-bs-toggle="dropdown"
              aria-label="Toggle theme (auto)">
        <svg class="bi my-1 theme-icon-active" width="1em" height="1em"><use href="#circle-half"></use></svg>
        <span class="visually-hidden" id="bd-theme-text">Toggle theme</span>
      </button>
      <ul class="dropdown-menu dropdown-menu-end shadow" aria-labelledby="bd-theme-text">
        <li>
          <button type="button" class="dropdown-item d-flex align-items-center" data-bs-theme-value="light" aria-pressed="false">
            <svg class="bi me-2 opacity-50 theme-icon" width="1em" height="1em"><use href="#sun-fill"></use></svg>
            Light
            <svg class="bi ms-auto d-none" width="1em" height="1em"><use href="#check2"></use></svg>
          </button>
        </li>
        <li>
          <button type="button" class="dropdown-item d-flex align-items-center" data-bs-theme-value="dark" aria-pressed="false">
            <svg class="bi me-2 opacity-50 theme-icon" width="1em" height="1em"><use href="#moon-stars-fill"></use></svg>
            Dark
            <svg class="bi ms-auto d-none" width="1em" height="1em"><use href="#check2"></use></svg>
          </button>
        </li>
        <li>
          <button type="button" class="dropdown-item d-flex align-items-center active" data-bs-theme-value="auto" aria-pressed="true">
            <svg class="bi me-2 opacity-50 theme-icon" width="1em" height="1em"><use href="#circle-half"></use></svg>
            Auto
            <svg class="bi ms-auto d-none" width="1em" height="1em"><use href="#check2"></use></svg>
          </button>
        </li>
      </ul>
    </div>

    
<main class="form-signin w-100 m-auto">
  <form action="userregister" method="POST" onsubmit="return validateForm()">
    <h1 class="h3 mb-3 fw-normal">ユーザー登録</h1>
    
    </div>

    <div class="form-floating">
      <input type="text" class="form-control" id="name" name="name" placeholder="ユーザー名">
      <label for="floatingInput">ユーザー名</label>
      <p id="nameMessage" style="height: 1.5em;"></p> <!-- メッセージ表示領域を追加 -->
    </div>
    
     <div class="form-floating">
      <input type="text" class="form-control" id="email" name="email" placeholder="メールアドレス">
      <label for="floatingInput">メールアドレス</label>
    </div>
    
    <div class="form-floating">
      <select class="form-select" id="sex" name="sex">
		  <option selected>性別</option>
		  <option value="男">男</option>
		  <option value="女">女</option>
		</select>
    </div>
    
    <div class="form-floating">
      <input type="password" class="form-control" id="password" name="password" placeholder="パスワード">
      <label for="floatingPassword">パスワード</label>
    </div>
    
    <div class="form-floating">
      <input type="password" class="form-control" id="rpassword" name="rpassword" placeholder="パスワード再入力">
      <label for="floatingPassword">パスワード再入力</label>
    </div>
    
    <p id="message" style="height: 1.5em;"></p> <!-- メッセージ表示領域を追加 -->

    <button class="btn btn-primary w-100 py-2" type="submit">登録</button>
    <p> </p>
    <label class="form-check-label" for="flexCheckDefault">
        すでにアカウントをお持ちの場合 
      </label>
    <p><a href="userlogin">ログイン</a></p>
    <p class="mt-5 mb-3 text-body-secondary">&copy; 2023–2024</p>
  </form>
</main>
<script src="/kaikei/docs/5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

    </body>
</html>
