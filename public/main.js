var socket;

function start() {
    var ws = new SockJS('/socket')
    socket = Stomp.over(ws)

    socket.connect({}, onSocketConnected)
}

function onSocketConnected() {
    socket.subscribe('/topic/chat', onReceiveMessage)
    //socket.subscribe('/topic/chat', sendMessage)
}

function sendMessage(mess) {
    var t = timeNow();
    var s = JSON.stringify({message: $('#msg').val(), time: t, user: "Mike"});
    socket.send("/topic/chat", {}, s);
    //socket.send('/chat', {}, JSON.stringify({message: $('#msg').val()}));
    //socket.send('/app/chat', {}, JSON.stringify({message: $('#msg').val()}));
}

function onReceiveMessage(mess) {
data = JSON.parse(mess.body)
    $('#divList').append("<div id='divlabel'>" + data.user + "<br />" + data.time + "</div>   <div id='divmsg'>" + data.msg +  "</div><br />");
    if (mess === undefined)
    {
        return;
    }
// ?? empty
//    data = JSON.parse(message.body);
//    document.getElementById('newMessage').appendChild(document.createTextNode(data));
}

function timeNow() {
    var d = new Date(),
        h = d.getHours(),
        m = d.getMinutes(),
    return h + ':' + m;
}

start();
