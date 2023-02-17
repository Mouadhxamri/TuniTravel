
$(document).ready(function() {
    $('#datatable').dataTable();
    $('#datatable-keytable').DataTable( { keys: true } );
    $('#datatable-responsive').DataTable();
    $('#datatable-scroller').DataTable( { ajax: "assets/plugins/datatables/json/scroller-demo.json", deferRender: true, scrollY: 380, scrollCollapse: true, scroller: true } );
    var table = $('#datatable-fixed-header').DataTable( { fixedHeader: true } );
} );
TableManageButtons.init();

