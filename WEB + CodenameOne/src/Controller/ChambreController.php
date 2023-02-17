<?php

namespace App\Controller;

use App\Entity\Chambre;
use App\Form\ChambreType;
use App\Repository\ChambreRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/chambre")
 */
class ChambreController extends AbstractController
{
    /**
     * @Route("/", name="app_chambre_index", methods={"GET"})
     */
    public function index(ChambreRepository $chambreRepository): Response
    {
        return $this->render('chambre/index.html.twig', [
            'chambres' => $chambreRepository->findAll(),
        ]);
    }
    
    /**
     * @Route("/cbh", name="app_chambre1_index", methods={"GET"})
     */
    public function showch(ChambreRepository $chambreRepository,$id): Response
    {
        $chambre= $this->getDoctrine()->getRepository(chambre::class)->find($id);
        return $this->render('reservation_hotel/addreservationhotel.html.twig', array("id" => $id, [
            'chambres' =>  $chambreRepository->findAll($id),
        ]));
    }

      
    /**
     * @Route("/add", name="app_chambre_new", methods={"GET", "POST"})
     */
    public function new(Request $request, ChambreRepository $chambreRepository): Response
    {
        $chambre = new Chambre();
        $form = $this->createForm(ChambreType::class, $chambre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $chambreRepository->add($chambre);
            return $this->redirectToRoute('app_chambre_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('chambre/new.html.twig', [
            'chambre' => $chambre,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="app_chambre_show", methods={"GET"})
     */
    public function show(Chambre $chambre): Response
    {
        return $this->render('chambre/show.html.twig', [
            'chambre' => $chambre,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="app_chambre_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Chambre $chambre, ChambreRepository $chambreRepository): Response
    {
        $form = $this->createForm(ChambreType::class, $chambre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $chambreRepository->add($chambre);
            return $this->redirectToRoute('app_chambre_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('chambre/edit.html.twig', [
            'chambre' => $chambre,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/deletechambre/{id}", name="app_chambre_delete")
     */
    public function delete(Request $request,$id): Response
    {
       
            $chambre= $this->getDoctrine()->getRepository(chambre::class)->find($id);
    
    
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($chambre);
            $entityManager->flush();
    
            $response = new Response();
            $response->send();
            return $this->redirectToRoute('app_chambre_index');
    }
}
