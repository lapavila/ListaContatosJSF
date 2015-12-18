/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var txtOut;;
var chat;

var activeClients = [];

var addClient = function(telefone) {
    
    var emAtendimento = activeClients.some(function(element) {
        return element.telefone == telefone
    });
    
    if (emAtendimento) {
        var cliente = clients.filter(function(element) {
           return element.telefone === telefone; 
        });
        
        activeClients.push(cliente);
    }
};

var createChat = function() {
    
    this.socket = null;
    
    var _connect = function(host) {
        if ('WebSocket' in window) {
            this.socket = new WebSocket(host);
        } else if ('MozWebSocket' in window) {
            this.socket = new MozWebSocket(host);
        } else {
            console.log('Error: WebSocket is not supported by this browser.');
            return;
        }

        this.socket.onopen = function () {
            $('.sf-flash').sfFlash();
            $('.sf-flash').html("servidor conectado");
        };

        this.socket.onclose = function () {
        };

        this.socket.onmessage = function (message) {
            console.log(message);
            var cliente = message.data.split(";");
            $( "#nome" ).val(cliente[0]);
            $( "#telefone" ).val(cliente[1]);
            if (cliente[0]) {
                $('.sf-flash').sfFlash();
                $('body').append('<div class="sf-flash">' + cliente[0] + " - " + cliente[1] + '</div>');
                window.location.href = "./cliente/1";
            }
        };
    };

    var _initialize = function() {
        if (window.location.protocol === 'http:') {
            _connect('ws://' + window.location.host + '/obelix/asterisk');
        } else {
            _connect('wss://' + window.location.host + '/obelix/asterisk');
        }
    };
    
    var _sendMessage = function(message) {
        socket.send(message);
    };

    return {
        initialize: _initialize,
        sendMessage: _sendMessage
    };
};

function init() {
    console.log('ws://' + window.location.host + '/obelix/asterisk');
    chat = createChat();
    chat.initialize();
}

