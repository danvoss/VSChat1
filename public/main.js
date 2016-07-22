var socket;

function start() {
    var ws = new SockJS("/chat")
    socket = Stomp.over(ws)

    socket.connect({}, onSocketConnected)
}

function onSocketConnected() {
    socket.subscribe("/topic/chat", sendMessage)
}

function sendMessage() {
    socket.send("/app/chat", {}, $('#msg').val());
}