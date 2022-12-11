<?php
class Json
{
    private $jsonPath;

    public function __construct($jsonPath)
    {
        $this->jsonPath = $jsonPath;
    }

    public function read($id)
    {
        $json = file_get_contents($this->jsonPath);
        $content = json_decode($json, true);
        $items = array_filter($content, function ($item) use ($id) {
            return (!empty($item['id']) && $item['id'] == $id);
        });
        $data = array_values($items)[0];

        return empty($data) ? false : $data;
    }

    public function create($item)
    {
        if ($this->read($item['id'])) {
            return false;
        }

        $json = file_get_contents($this->jsonPath);
        if ($json == null) {
            $content = [];
        } else {
            $content = json_decode($json, true);
        }

        array_push($content, $item);
        file_put_contents($this->jsonPath, json_encode($content));
        return true;
    }

    public function delete($item)
    {
        if ($this->read($item['id'])) {
            $json = file_get_contents($this->jsonPath);
            $content = json_decode($json, true);
            $id = $item['id'];
            $items = array_filter($content, function ($item) use ($id) {
                return (!empty($item['id']) && $item['id'] == $id);
            });

            unset($content[array_search($items[0], $content)]);
            file_put_contents($this->jsonPath, json_encode($content));
        }
    }
}
