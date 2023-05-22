
var heatBar = {
    context: null,
    canvas: null,

    blip: {
        width: 100, //px
        colour: {r: 255, g: 0, b: 0}
    },

    // Set everything up
    init: function(canvasObject)
    {
        // Add the canvas object to the hexGrid object
        this.canvas = canvasObject;

        // Get a drawing context
        this.context = this.canvas.getContext('2d');

    },

    getBlipColour: function(opacity) {
        return 'rgba('+this.blip.colour.r+','+this.blip.colour.g+','+this.blip.colour.b+', '+opacity+')';
    },

    drawGradient: function(posX)
    {
        var gradient = this.context.createLinearGradient((posX-(this.blip.width/2)),0,(posX+(this.blip.width/2)),0);
        gradient.addColorStop(0, this.getBlipColour(0));
        gradient.addColorStop(0.35, this.getBlipColour(0.07));
        gradient.addColorStop(0.5, this.getBlipColour(0.1));
        gradient.addColorStop(0.65, this.getBlipColour(0.07));
        gradient.addColorStop(1, this.getBlipColour(0));
        this.context.fillStyle = gradient;
        this.context.fillRect(0,0,(posX+this.blip.width/2),30);
    }

};