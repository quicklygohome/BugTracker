$(document).ready(function() {
    $(function() {
        $("#headOfPrj").autocomplete({
            source : function(request, response) {
                $.ajax({
                    url : "UserAutocompleteServlet",
                    type : "GET",
                    data : {
                        term : request.term
                    },
                    dataType : "json",
                    success : function(data) {
                        response(data);
                    }
                });
            }
        });
    });
});