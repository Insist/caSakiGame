<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/5/10
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<hr>
<%--<input id="text" type="text"/>--%>
<%--<button onclick="send()">Send</button>--%>
<%--<button onclick="closeWebSocket()">Close</button>--%>
<div id="hallDiv">

    房间列表:<div id="rooms"></div>
    <div id="message">
    </div>
    <hr>
    <input id="create_room_name"/>
    <button onclick="createRoom()">创建房间</button>
</div>

<div id="roomDiv" style="display: none;">
    玩家:<div id="roomUsers">

    </div>
    <div id="roomMessage">

    </div>
    <button id="startBtn" onclick="startGame()" style="display: none;">开始</button>
    <button id="readyBtn" onclick="readyGame()">准备</button>
    <button id="exitBtn" onclick="exitGame()">离开</button>
</div>


<div id="gameDiv" style="display: none;">
    局号:<div id="gameRoundNum"></div>
    玩家信息:<div id="gameUsers"></div>
    当前场牌:<div id="centerCard"></div>
    手牌:<div id="handCard"></div>
    操作:<div>
    <div id="game_select_color" style="display: none;">
        <button id="colorBtn_1" onclick="selectColor(1)">红</button>
        <button id="colorBtn_2" onclick="selectColor(2)">绿</button>
        <button id="colorBtn_3" onclick="selectColor(3)">蓝</button>
        <button id="colorBtn_4" onclick="selectColor(4)">黄</button>
    </div>
    <button id="actionBtn_1" class="action" onclick="runAction(1)">跳过</button>
    <button id="actionBtn_2" class="action" onclick="checkGame()">质疑</button>
    <button id="actionBtn_3" class="action" onclick="judgeGame()">举报</button>
    <button id="actionBtn_4" class="action" onclick="unoGame()">uno</button>
    <label id="countDownLabel"></label>
    </div>

    <div id="gameMessage">

    </div>
</div>
</body>
<script src="/static/modules/jquery-1.11.3.min.js"></script>
<script src="/static/modules/json2.js"></script>
<script src="/static/modules/global.js"></script>
<script>
    var baseUrl = "${ baseUrl }";
    var websocket = null;
    var PLAYERS;
    var countDownInterval = 0;
    var centerCard;
    $(document).ready(function () {

        //判断当前浏览器是否支持WebSocket
        if ('WebSocket' in window) {
            websocket = new WebSocket("ws://"+baseUrl+"/websocket");
        } else {
            alert('该浏览器不支持websocket')
        }

        //连接发生错误的回调方法
        websocket.onerror = function () {
//            setMessageInnerHTML("error");
            console.log("socket error");
        };

        //连接成功建立的回调方法
        websocket.onopen = function (event) {
//            setMessageInnerHTML("open");
            UID = getCookie("uno-uid");
            TOKEN = getCookie("uno-token");
            console.log("socket open");
            checkAuth();
            getRooms();
        }

        //接收到消息的回调方法
        websocket.onmessage = function (event) {
            var data = JSON.parse(event.data);
            console.log(data);
            if(data.type=="getRooms"){
                $("#rooms").empty();
                for(i=0;i<data.data.length;i++){
                    var roomInfo = data.data[i];
                    addRoomHTML(roomInfo);
                }
            } else if(data.type=="addRoom"){
                addRoomHTML(data.data);
            } else if(data.type=="auth"){
                if(data.code!=0){
                    websocket.close();
                }
            } else if(data.type=="showHall"){
                showDiv("hall");
                getRooms();
            } else if(data.type=="showRoom"){
                showDiv("room");
                $("#roomUsers").empty();
                $("#roomMessage").empty();
                PLAYERS = data.data.players;
                for(i=0;i<data.data.players.length;i++){
                    var userInfo = data.data.players[i];
                    addUserHTML(userInfo);
                }
            } else if(data.type=="recJoinRoom"){
                addUserHTML(data.data);
            } else if(data.type=="exitRoom"){
                removeUserHTML(data.data);
            } else if(data.type=="createRoom"){
                $("#readyBtn").hide();
                $("#startBtn").show();
            } else if(data.type=="getReady"){
                $("#roomUser_"+data.data.userId).html($("#roomUser_"+data.data.userId).html()+"(准备)");
            } else if(data.type=="startGame"){
                showDiv("game");
            } else if(data.type=="initGame"){
                for(i=0;i<data.data.players.length;i++){
                    var userInfo = data.data.players[i];
                    addPlayerHTML(userInfo);
                }
            } else if(data.type=="updateRound"){
                $("#gameRoundNum").html(data.data.round);
            } else if(data.type=="startHandCard" || data.type=="updateHandCard"){
                $("#handCard").empty();
                for(i=0;i<data.data.length;i++){
                    var userInfo = data.data[i];
                    addCardHTML(userInfo);
                }
                handCards = data.data;
                disableAllAction();
            } else if(data.type=="updatePlayerInfo"){
                updatePlayerHTML(data.data);
            } else if(data.type=="doAction"){
                countDownSecend = 30;
                clearInterval(countDownInterval);
                countDownInterval = setInterval("countDown()",1000);
                for(i=0;i<handCards.length;i++){
                    o=handCards[i];
                    if(centerCard.type==1) {
                        if(o.color == centerCard.color || o.number == centerCard.number){
                            $("#handCard_"+ o.id).removeAttr("disabled");
                        }else if(o.type==3){
                            $("#handCard_"+ o.id).removeAttr("disabled");
                        }
                    }else if(centerCard.type==2){
                        if(centerCard.number == 12 && centerCard.isFirst==true){
                            if(o.number == 12 || o.number == 14){
                                $("#handCard_"+ o.id).removeAttr("disabled");
                            }
                        }else if(o.color == centerCard.color || o.number == centerCard.number){
                            $("#handCard_"+ o.id).removeAttr("disabled");
                        }
                    }else if(centerCard.type==3){
                        if(centerCard.number == 13){
                            if(o.color == centerCard.tmpColor){
                                $("#handCard_"+ o.id).removeAttr("disabled");
                            }else if(o.type==3){
                                $("#handCard_"+ o.id).removeAttr("disabled");
                            }
                        }else if(centerCard.number == 14){
                            if(centerCard.isFirst==true){
                                if(o.number == 14){
                                    $("#handCard_"+ o.id).removeAttr("disabled");
                                }
                            }else{
                                if(o.color == centerCard.tmpColor){
                                    $("#handCard_"+ o.id).removeAttr("disabled");
                                }else if(o.type==3){
                                    $("#handCard_"+ o.id).removeAttr("disabled");
                                }
                            }
                        }
                    }
                }
                for(i=0;i<data.data.length;i++){
                    o=data.data[i];
                    $("#actionBtn_"+o).removeAttr("disabled");
                }
            } else if(data.type="updateCenterCard"){
                if(data.data!=null){
                    centerCard = data.data;
                    $("#centerCard").html(getCardDescript(centerCard));
                }
            }
        }

        function showDiv(divName){
            $("#hallDiv").hide();
            $("#roomDiv").hide();
            $("#gameDiv").hide();
            $("#"+divName+"Div").show();
        }

        //连接关闭的回调方法
        websocket.onclose = function (evt) {
//            setMessageInnerHTML("close");
            console.log("socket close");
            console.log(evt);
            alert("连接断开");
//            window.location = BASE_URL+"/index.do";
        }

        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function () {
            websocket.close();
        }

//        getRooms();
    });


    function countDown(){
        $("#countDownLabel").html(countDownSecend);
        countDownSecend--;
        if(countDownSecend<1){
            $("#countDownLabel").html("");
            clearInterval(countDownInterval);
            disableAllAction();
        }
    }

    function disableAllAction(){
        $(".action").each(function(o){
            $(this).attr("disabled","disabled");
        });
    }
    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }

    function addRoomHTML(roomInfo){
        $("#rooms").append("<a href='javascript:void(0)' onclick='joinRoom("+roomInfo.roomId+")'>"+roomInfo.roomId+":"+roomInfo.roomName+"</a><br>");
    }

    function addUserHTML(userInfo){
        var roomleader = "";
        if(userInfo.isLeader){
            roomleader="(房主)";
        }
        $("#roomUsers").append("<a id='roomUser_"+userInfo.userId+"' href='#'>"+userInfo.index+":"+userInfo.userName+roomleader+"</a><br>");
        $("#roomMessage").append(userInfo.userName+"加入了房间<br>")
    }

    function removeUserHTML(userInfo){
        $("#roomUser_"+userInfo.userId).remove();
        $("#roomMessage").append(userInfo.userName+"离开了房间<br>")
    }

    function addPlayerHTML(playerInfo){
        $("#gameUsers").append("<div id='game_player_"+playerInfo.index+"'>"+playerInfo.userName+":"+playerInfo.handCardNum+":"+playerInfo.score+"</div>");
    }
    function updatePlayerHTML(playerInfo){
        $("#game_player_"+playerInfo.index).empty();
        $("#game_player_"+playerInfo.index).append(playerInfo.userName+":"+playerInfo.handCardNum+":"+playerInfo.score+"("+playerInfo.isOffline?"离线":"在线"+")");
    }

    function addCardHTML(card){
        $("#handCard").append("<button id='handCard_"+card.id+"' class='action' onclick='playCard("+card.id+")'>"+getCardDescript(card)+"</button>&nbsp");
    }

    function getCardDescript(card){
        if(card.type==1){
            return getCardColor(card.color)+card.number;
        }
        if(card.type==2){
            if(card.number == 10){
                return getCardColor(card.color)+"跳";
            }
            if(card.number == 11){
                return getCardColor(card.color)+"反";
            }
            if(card.number == 12){
                return getCardColor(card.color)+"+2";
            }
        }
        if(card.type==3){
            if(card.number == 13){
                if(card.tmpColor&&card.tmpColor!=0)
                    return "彩（"+getCardColor(card.tmpColor)+"）";
                return "彩";
            }
            if(card.number == 14){
                if(card.tmpColor&&card.tmpColor!=0)
                    return "彩+4（"+getCardColor(card.tmpColor)+"）";
                return "彩+4";
            }
        }
    }

    function getCardColor(color){
        if(color==1)return "红";
        if(color==2)return "绿";
        if(color==3)return "蓝";
        if(color==4)return "黄";
    }

    //关闭连接
    function closeWebSocket() {
        websocket.close();
    }

    //发送消息
    function createRoom() {
        var message = JSON.stringify({type: "createRoom",roomName:$("#create_room_name").val()});
        sendMessage(message);
    }

    //发送消息
    function getRooms() {
        var message = JSON.stringify({type: "getRooms"});
        sendMessage(message);
    }

    function checkAuth(){
        var message = JSON.stringify({type: "auth",uid:getCookie("uno-uid"),token:getCookie("uno-token")});
        sendMessage(message);
    }

    function joinRoom(id){
        var message = JSON.stringify({type: "joinRoom",roomId:id});
        sendMessage(message);
    }

    var tmpCardId;
    function playCard(id){
        if(id >= 100){
            tmpCardId = id;
            $("#game_select_color").show();
        } else{
            var message = JSON.stringify({type: "runAction",actionType:0,cardId:id});
            sendMessage(message);
        }
    }

    function selectColor(color){
        var message = JSON.stringify({type: "runAction",actionType:0,cardId:tmpCardId,tmpColor:color});
        sendMessage(message);
        $("#game_select_color").hide();
    }

    function runAction(type){
        var message = JSON.stringify({type: "runAction",actionType:type});
        sendMessage(message);
    }

    function sendMessage(message){
        if(UID != getCookie("uno-uid")||TOKEN != getCookie("uno-token")){
            alert("登录失效");
            websocket.close();
            return;
        }
        websocket.send(message);
    }

    function startGame(){
//        if(PLAYERS.length<4){
//            alert("至少要大于4人才能开始");
//            return;
//        }
        for(var i=0;i<PLAYERS;i++){
            var player = PLAYERS[i];
            if(player.ready == false){
                alert("还有玩家没有准备");
                return;
            }
        }
        var message = JSON.stringify({type: "startRoom"});
        sendMessage(message);
        $("#startBtn").attr("disabled","disabled");
    }

    function readyGame(){
        var message = JSON.stringify({type: "readyRoom"});
        sendMessage(message);
        $("#readyBtn").attr("disabled","disabled");
    }

    function exitGame(){
        var message = JSON.stringify({type: "exitRoom"});
        sendMessage(message);
    }

</script>
</html>
