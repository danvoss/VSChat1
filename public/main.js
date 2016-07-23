var socket;

function start() {
    var ws = new SockJS('/socket')
    socket = Stomp.over(ws)

    socket.connect({}, onSocketConnected)
}

function onSocketConnected() {
    socket.subscribe('/chat', onReceiveMessage)
    //socket.subscribe('/topic/chat', sendMessage)
}

function sendMessage() {
    var s = JSON.stringify({message: $('#msg').val()});
    socket.send("/topic/chat", {}, s);
    //socket.send('/chat', {}, JSON.stringify({message: $('#msg').val()}));
    //socket.send('/app/chat', {}, JSON.stringify({message: $('#msg').val()}));
}

function onReceiveMessage(message) {
    data = JSON.parse(message.body); // empty
    document.getElementById('newMessage').appendChild(document.createTextNode(data));


}

function showMessage() {
}

start();
