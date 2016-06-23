<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<style>
</style>
<body>
    <div id="all">
        <div v-show="register" >
            <input v-model="username" placeholder="input username"/><br>
            <input type="password" v-model="password" placeholder="input password"/><br>
            <input type="password" v-model="passwordAg" placeholder="input password again"/><br>
            <input type="button" @click="submitReg" value="注册">
            <input type="button" @click="showLogin" value="返回登录">  <label>{{regMessage}}</label>
        </div>
        <div v-show="!register" id="loginForm">
            <input name="log_username"/><br>
            <input type="password" name="log_password"/><br>
            <input type="button" @click="submitLog" value="登录">
            <input type="button" @click="showReg" value="注册"> <label id="log_message"></label>
        </div>
    </div>
    <script src="/static/modules/jquery-1.11.3.min.js"></script>
    <script src="/static/modules/json2.js"></script>
    <script src="/static/modules/global.js"></script>
    <script src="/static/modules/vue.js"></script>

    <script type="text/javascript">
        var submitRegFunc = function(){
            var username = allData.username;
            var password = allData.password;
            var passwordAg = allData.passwordAg;
            if(username.length<6){
                allData.regMessage = "账号错误";
                return;
            }
            if(password.length<6||password!=passwordAg){
                allData.regMessage = "密码错误";
                return;
            }
            var htmlobj = $.ajax({url:BASE_URL+"/user/register.do",data:{
                username:username,
                password:password
            },type:"post",async:false});
            var result = JSON.parse(htmlobj.responseText);
            allData.regMessage = (result.message);
            if(result.code==0){
                allData.register = false;
            }
        };


        function submitLogFunc(){
            var username = $("input[name='log_username']").val();
            var password = $("input[name='log_password']").val();
            if(username.length<6){
                alert("账号错误");
                return;
            }
            if(password.length<6){
                alert("密码错误");
                return;
            }
            var htmlobj = $.ajax({url:BASE_URL+"/user/login.do",data:{
                username:username,
                password:password
            },type:"post",async:false});
            var result = JSON.parse(htmlobj.responseText);
            if(result.code==0){
                window.location = BASE_URL+"/game.do";
            }else{
                $("#log_message").html(result.message);
            }
        }

        var allData = {
            register:false,
            regMessage:'',
            username:'',
            password:'',
            passwordAg:'',
            submitReg:submitRegFunc,
            submitLog:submitLogFunc,
            showReg:function(){
                allData.register=true;
            },
            showLogin:function(){
                allData.register=false;
            }
        };
        new Vue({
            el: '#all',
            data: allData
        });

    </script>
</body>