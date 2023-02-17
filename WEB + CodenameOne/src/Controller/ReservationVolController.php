<?php

namespace App\Controller;

use App\Entity\ReservationVol;
use App\Entity\Vol;
use App\Form\ReservationVolType;
use Doctrine\ORM\EntityManagerInterface;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;

/**
 * @Route("/reservation/vol")
 */
class ReservationVolController extends AbstractController
{
    /**
     * @Route("/", name="app_reservation_vol_index", methods={"GET"})
     * @IsGranted("ROLE_USER")
     */
    public function index(EntityManagerInterface $entityManager, PaginatorInterface $paginator, Request $request): Response
    {
        $resVols = $entityManager
            ->getRepository(ReservationVol::class)
            ->findAll();
            $reservationVols = $paginator->paginate(
                $resVols,
                $request->query->getInt('page',1) , 5
            );

        return $this->render('reservation_vol/index.html.twig', [
            'reservation_vols' => $reservationVols,
        ]);
    }

      /**
     * @Route("/list", name="listvols", methods={"GET"})
     * @IsGranted("ROLE_USER")
     */
    public function listvols(EntityManagerInterface $entityManager): Response
    {
        $vols = $entityManager
            ->getRepository(Vol::class)
            ->findAll();

        return $this->render('vol/listVol.html.twig', [
            'vols' => $vols,
        ]);
    }

    /**
     * @Route("/new/{idVol}", name="app_reservation_vol_new", methods={"GET", "POST"})
     * @IsGranted("ROLE_USER")
     */
    public function new(\Symfony\Component\HttpFoundation\Request $request, EntityManagerInterface $entityManager,$idVol): Response
    {
       // $em = $this->getDoctrine()->getManager();

        $reservationVol = new ReservationVol();
        $form = $this->createForm(ReservationVolType::class, $reservationVol);
        
        $form->handleRequest($request);
      //  $idVol = $_GET['idVol'];
     //   $Vol = $entityManager
     //   ->getRepository(Vol::class) 
      //  ->find($idVol);
        

        if ($form->isSubmitted() && $form->isValid()) {
            
            $entityManager->persist($reservationVol);


            $entityManager->flush();

            return $this->redirectToRoute('app_reservation_vol_index');
        }

        return $this->render('reservation_vol/new.html.twig', [
            'reservation_vol' => $reservationVol,
            'form' => $form->createView(), "idVol" => $idVol,
            // 'vol' => $vol
        ]);
    }

    /**
     * @Route("/{idResv}", name="app_reservation_vol_show", methods={"GET"})
     * @IsGranted("ROLE_ADMIN")
     */
    public function show(ReservationVol $reservationVol): Response
    {
        return $this->render('reservation_vol/show.html.twig', [
            'reservation_vol' => $reservationVol,
        ]);
    }

    /**
     * @Route("/{idResv}/edit", name="app_reservation_vol_edit", methods={"GET", "POST"})
     * @IsGranted("ROLE_ADMIN")
     */
    public function edit(Request $request, ReservationVol $reservationVol, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(ReservationVolType::class, $reservationVol);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_reservation_vol_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reservation_vol/edit.html.twig', [
            'reservation_vol' => $reservationVol,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idResv}", name="app_reservation_vol_delete", methods={"POST"})
     * @IsGranted("ROLE_ADMIN")
     */
    public function delete(Request $request, ReservationVol $reservationVol, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$reservationVol->getIdResv(), $request->request->get('_token'))) {
            $entityManager->remove($reservationVol);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_reservation_vol_index', [], Response::HTTP_SEE_OTHER);
    }
}
