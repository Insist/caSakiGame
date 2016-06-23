<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<style>
    #img{
        -webkit-transform-origin:bottom left;
        -webkit-transform: rotateY(60deg);
    }
</style>
<body>
    <div id="app">
        <p>{{ message }}</p>
        <button @click="reverseMessage">Reverse Message</button>
    </div>

    <div id="all">
        <div v-show="layer==1" style="display: none">
            123
        </div>
        <div v-show="layer==2" style="display: none">
            456
        </div>
        <button @click="changeLayer">changeLayer</button>
    </div>

    <script src="/static/modules/jquery-1.11.3.min.js"></script>
    <script src="/static/modules/json2.js"></script>
    <script src="/static/modules/global.js"></script>
    <script src="/static/modules/vue.js"></script>
    <script type="text/javascript">
        var data = {
            message: 'Hello Vue.js!'
        };
        new Vue({
            el: '#app',
            data: data,
            methods: {
                reverseMessage: function () {
                    this.message = this.message.split('').reverse().join('')
                }
            }
        });

        new Vue({
            el: '#all',
            data: {layer:2},
            methods: {
                changeLayer: function () {
                    this.layer = this.layer==1?2:1;
                }
            }
        });
    </script>
</body>