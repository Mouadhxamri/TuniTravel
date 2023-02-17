<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use App\Form\ChangeNameType;
use App\Form\ChangeMailType;
use App\Form\ChangePasswordType;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use App\Entity\User;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;

class ProfileController extends AbstractController
{
    /**
     * @Route("/profile", name="app_profile")
     * @IsGranted("ROLE_USER")
     */

    public function index(Request $request): Response
    {
        $user = $this->getUser();
        
        $form = $this->createForm(ChangeNameType::class, $user);
        $form->handleRequest($request);

        $emailform = $this->createForm(ChangeMailType::class, $user);
        $emailform->handleRequest($request);

        $passwordform = $this->createForm(ChangePasswordType::class, $user);
        $passwordform->handleRequest($request);

        if($form->isSubmitted() && $form->isValid()){
            $em = $this->getDoctrine()->getManager();
            $em->persist($user);
            $em->flush();

            $this->addFlash('message', 'Profil mis à jour');
            return $this->redirectToRoute('app_profile');
        }
        if($emailform->isSubmitted() && $emailform->isValid()){
            $em = $this->getDoctrine()->getManager();
            $em->persist($user);
            $em->flush();

            $this->addFlash('message', 'Profil mis à jour');
            return $this->redirectToRoute('app_profile');
        }
        if($passwordform->isSubmitted() && $passwordform->isValid()){
            $em = $this->getDoctrine()->getManager();
            $em->persist($user);
            $em->flush();

            $this->addFlash('message', 'Profil mis à jour');
            return $this->redirectToRoute('app_profile');
        }

        return $this->render('profile/index.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
            'emailform' => $emailform->createView(),
            'passwordform' => $passwordform->createView(),

        ]);
    } 

    /**
     * @Route("/block", name="block")
      *@IsGranted("ROLE_BLOQUE")
     */
    public function page404(){
    
        return $this->render('profile/block.html.twig',[
          
        ]);
    }


}
