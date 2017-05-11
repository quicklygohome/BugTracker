/**
 * Created by Sasha on 08.05.2017.
 */
function login() {
    $(document).on("submit", $form.attr("id"), function(event) {
        var $form = $(this);

        $.post($form.attr("action"), $form.serialize(), function(response) {
            alert(response);
        });

        event.preventDefault(); // Important! Prevents submitting the form.
    });
}