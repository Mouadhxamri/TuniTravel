<?php

namespace App\service;

use Dompdf\Dompdf;
use Dompdf\Options;

class Pdfservice
{
    private $domPdf;

    public function __construct(){
        $this->domPdf = new Dompdf();

        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont','Arial');
        $pdfOptions->setIsHtml5ParserEnabled(true);

        $this->domPdf->setOptions($pdfOptions);

    }

    public function showPdfFile($html){
        $this->domPdf->loadHtml($html);
        $this->domPdf->render();
    }
}