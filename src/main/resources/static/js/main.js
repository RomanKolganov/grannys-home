$(document).ready(function() {
    $(".ui.fluid.selection.dropdown").dropdown({
        clearable: true
    });
    $(".ui.styled.accordion").accordion();
});

function deleteEntity(url, table) {
    $.ajax({
        url: url,
        type: 'DELETE',
        success: function () {
            table.DataTable().ajax.reload();
        },
        error: function() {
            $('#accessDeniedSidebar').sidebar('toggle');
        }
    });
}

function createEntity(url, data, table) {
    $.ajax({
        url: url,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function () {
            table.DataTable().ajax.reload();
        },
        error: function() {
            $('#accessDeniedSidebar').sidebar('toggle');
        }
    });
}

function updateEntity(url, data, table) {
    $.ajax({
        url: url,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function () {
            table.DataTable().ajax.reload();
        },
        error: function() {
            $('#accessDeniedSidebar').sidebar('toggle');
        }
    });
}