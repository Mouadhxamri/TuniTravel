<?php

namespace App\Controller;

use App\Entity\ReservationHotel;
use App\Form\ReservationHotelType;
use App\Repository\ChambreRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;
use Swift_Mailer;
use Swift_Message;

class ReservationHotelController extends AbstractController
{
    /**
     * @Route("/reservationhotel", name="app_reservation_hotel")
     * @IsGranted("ROLE_USER")
     */
    public function index(): Response
    {
        return $this->render('reservation_hotel/showhotel.html.twig', [
            'controller_name' => 'ReservationHotelController',
        ]);
    }
   /**
     * @Route("/lesreservationshotel", name="lesreservationshotel")
     * @IsGranted("ROLE_ADMIN")
     */
    public function showreservations1(): Response
    {
        $res = $this->getDoctrine()->getManager()->getRepository(ReservationHotel::class)->findAll();
        return $this->render('hotel/lesreservations.html.twig',array('reservationhotels'=>$res));
    }
    /**
     * @Route("/mesreservationhotel", name="app_reservation_hotel1")
     * @IsGranted("ROLE_USER")
     */
    public function showreservations(): Response
    {
        $res = $this->getDoctrine()->getManager()->getRepository(ReservationHotel::class)->findAll();
        return $this->render('reservation_hotel/showreservations.html.twig',array('reservationhotels'=>$res));
    }
    /**
     * @Route("/editreservationhotel/{id}", name="edit_reservationhotel")
     * Method({"GET", "POST"})
     * @IsGranted("ROLE_USER")
     */
    public function edit(\Symfony\Component\HttpFoundation\Request  $request, $id) {
        $ReservationHotel = new ReservationHotel();
        $ReservationHotel = $this->getDoctrine()->getRepository(ReservationHotel::class)->find($id);
        $em= $this->getDoctrine()->getManager();
        $ReservationHotel = $em->getRepository(ReservationHotel::class)->find($id);
        $form = $this->createForm(ReservationHotelType::class,$ReservationHotel);
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid()) {
            $em->flush();

            return $this->redirectToRoute('app_reservation_hotel');

        }

        return $this->render('reservation_hotel/editreservationhotel.html.twig',array("f"=>$form->createView()));
    }

  /**
     * @Route("/deletereservationhotel/{id}",name="delete_reservationhotel")
     * @IsGranted("ROLE_USER")
     */
    public function delete(Request $request, $id) {
        $ReservationHotel= $this->getDoctrine()->getRepository(ReservationHotel::class)->find($id);


        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->remove($ReservationHotel);
        $entityManager->flush();

        $response = new Response();
        $response->send();
        return $this->redirectToRoute('app_reservation_hotel');
    }
    /**
     * @Route("/deletereservationh/{id}",name="deletereservationh")
     * @IsGranted("ROLE_USER")
     */
    public function delete1(Request $request, $id) {
        $ReservationHotel= $this->getDoctrine()->getRepository(ReservationHotel::class)->find($id);


        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->remove($ReservationHotel);
        $entityManager->flush();

        $response = new Response();
        $response->send();
        return $this->redirectToRoute('lesreservationshotel');
    }
    /**
     * @Route("/addreservationhotel/{id}", name="addreservationhotel")
     * Method({"GET", "POST"})
     * @IsGranted("ROLE_USER")
     */
    public function addreservationhotel(\Symfony\Component\HttpFoundation\Request  $request,$id, ChambreRepository $chambreRepository,\Swift_Mailer $mailer): Response
    {
        $em = $this->getDoctrine()->getManager();
        $ReservationHotel = new ReservationHotel();
        $form= $this->createForm(ReservationHotelType::class,$ReservationHotel);

        $form->handleRequest($request);
        $a=$request->get('id');
        if($form->isSubmitted() && $form->isValid()) {

           
            $em->persist($ReservationHotel);
            /*
             * Commit
             */
           $em->flush();
           $message = (new \Swift_Message('Confirmation Reservation'))
           // On attribue l'expéditeur
           ->setFrom('tunitraveltech@gmail.com')
           // On attribue le destinataire
           ->setTo('mouadh.elamri@esprit.tn')
           ->setBody(
            "Votre Reservation a été effectué avec succes sous la reference NUM $a !
     Merci Pour choisir TuniTravel Agency! Si vous avez un probleme merci répondre a cet email :)"
                
            )
            
        
    ;
    //envoie le msg
    $mailer->send($message);

    //$this->addFlash('message', 'Votre message a été transmis, nous vous répondrons dans les meilleurs délais.');





            return  $this->redirectToRoute('app_reservation_hotel');
            
        }

        return $this->render('reservation_hotel/addreservationhotel.html.twig',
            array("f"=>$form->createView(), "id" => $id, 
            'chambres' =>  $chambreRepository->findAll(),
        
        ));

    }

}
