<?php
function createFormInput(string $type, string $name, string $class, string $id)
{
    return sprintf('<input type="%s" name="%s" class="%s" id="%s">', $type, $name, $class, $id);
}

function createFormLabel(string $for, string $name, string $class = '', string $content)
{
    return sprintf('<label for="%s" name="%s" class="%s">%s</label>', $for, $name, $class, $content);
}

function createFormLabelInput(string $class = '', string $id = '', string $name)
{
    return sprintf('<input type="text" class="%s" name="%s" id="%s">', $class, $name, $id);
}

function createFormElement(string $labelFor, string $labelName, string $labelClass = '', string $content, string $inputName = '', string $inputClass = '', string $inputId = '')
{
    return
        createFormLabel($labelFor, $labelName, $labelClass, $content) .
        createFormLabelInput($inputClass, $inputId, $inputName);
}

function createForm(string $action, string $method, string $class = '', string $content)
{
    return
        sprintf('<form action="%s" method="%s" class="%s">%s</form>', $action, $method, $class, $content);
}

function createDiv(string $class = '', string $id = '', string $content = '')
{
    return sprintf('<div class="%s" id="%s">%s</div>', $class, $id, $content);
}
