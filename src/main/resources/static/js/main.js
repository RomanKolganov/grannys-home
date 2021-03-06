$(document).ready(function() {
    $(".ui.fluid.selection.dropdown").dropdown({
        clearable: true
    });
    $(".ui.dropdown.item").dropdown();
    $(".ui.styled.accordion").accordion();
});

function deleteEntity(url, table) {
    $.ajax({
        url: url,
        type: 'DELETE',
        success: function () {
            table.DataTable().ajax.reload();
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
        }
    });
}