function saveRowColumnConfig(sourceId) {
    console.log("pivotSave detected");
    var config = $('#output').data('pivotUIOptions');
    var rows = config.rows.toString();    console.log('Selected Row:' + rows);
    var cols = config.cols.toString();
    
    try {
        PrimeFaces.ab({
            process:  '@form', 
            source:   sourceId, 
            event:    'pivotSave', 
            update:   'msgs',
            params: [
                        {name: 'org.metachart.savePivot.rows',				value: rows},
                        {name: 'org.metachart.savePivot.cols',				value: cols}
                    ],
            oncomplete: function(xhr, status, args) {console.log('pivotSave AJAX request sent.')}
        });
    } catch(e) {
            console.log("pivotSave failed." +e);
    }
        finally {}
};
