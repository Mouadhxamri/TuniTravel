<?php

namespace App\Controller;



use App\service\Pdfservice;
use App\Entity\RreservationVoiture;
use Stripe\Checkout\Session;
use Stripe\Stripe;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Routing\Generator\UrlGeneratorInterface;

class PaymentController extends AbstractController
{
    /**
     * @Route("/payment", name="app_payment"), methods={"GET"})
     */
    public function index(): Response
    {
        return $this->render('payment/index.html.twig', [
            'controller_name' => 'PaymentController'
        ]);
    }

    /**
     * @Route("/checkout", name="app_checkout")
     */
/*   public function checkout($stripeSK): Response
    {
        Stripe::setApiKey($stripeSK);

        $session = Session::create([
            'payment_method_types' => ['card'],
            'line_items'           => [
                [
                    'price_data' => [
                        'currency'     => 'usd',
                        'product_data' => [
                            'name' => 'Reservation Voiture',
                        ],
                        'unit_amount'  => 30000,
                    ],
                    'quantity'   => 1,
                ]
            ],
            'mode'                 => 'payment',
            'success_url'          => $this->generateUrl('app_success_url', [], UrlGeneratorInterface::ABSOLUTE_URL),
            'cancel_url'           => $this->generateUrl('app_success_url', [], UrlGeneratorInterface::ABSOLUTE_URL),
        ]);
        

        return $this->redirect($session->url, 303);
    } */
    

    /**
     * @Route("/pdf", name="app_payment_pdf")
     */
    public function generatePdf( Pdfservice $pdf)
    {
        $html = $this->render('payment/facture.html.twig');
        $pdf->showPdfFile($html);
        return $this->render('payment/facture.html.twig');
    }
    

    /**
     * @Route("/payment_success", name="app_success_url"), methods={"GET"})
     */

    public function successUrl(): Response
    {
      
        return $this->render('payment/success.html.twig', []);
    }

    /**
     * @Route("//cancel_url", name="app_cancel_url"), methods={"GET"})
     */
    public function cancelUrl(): Response
    {
        return $this->render('payment/cancel.html.twig', []);
    }

    
}
