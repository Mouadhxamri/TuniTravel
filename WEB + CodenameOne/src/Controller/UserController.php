<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\User2Type;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;


class UserController extends AbstractController
{
    /**
     * @Route("/user", name="app_user_index", methods={"GET", "POST"})
     */
    public function index(EntityManagerInterface $entityManager): Response
    {
        $users = $entityManager
            ->getRepository(User::class)
            ->findAll();

        return $this->render('user/index.html.twig', [
            'users' => $users,
        ]);
    }

    /**
     * @Route("/user/new", name="app_user_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $user = new User();
        $form = $this->createForm(User2Type::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($user);
            $entityManager->flush();

            return $this->redirectToRoute('app_user_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('user/new.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/user/{idUser}", name="app_user_show", methods={"GET"})
     */
    public function show(User $user): Response
    {
        return $this->render('user/show.html.twig', [
            'user' => $user,
        ]);
    }

    /**
     * @Route("/user/{idUser}/edit", name="app_user_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, User $user, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(User2Type::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_user_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('user/edit.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/user/{idUser}", name="app_user_delete", methods={"POST"})
     */
    public function delete(Request $request, User $user, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$user->getIdUser(), $request->request->get('_token'))) {
            $entityManager->remove($user);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_user_index', [], Response::HTTP_SEE_OTHER);
    }

        /**
    *@Route("/user/bloqueclient/{email}", name="blocage", methods={"GET", "POST"})
    * @IsGranted("ROLE_ADMIN")
    */
    public function bloquer(String $email,Request $request, User $user, EntityManagerInterface $entityManager): Response
    {
        $entityManager = $this->getDoctrine()->getManager();
        $users = $this->getDoctrine()->getRepository(User::class)->findBy(array('email' => $email));
            $user = $users[0];
            $user->setRole("client_bloque"); 
            $user->setRoles(["ROLE_BLOQUE"]);
        $entityManager->flush();
        return $this->redirectToRoute('app_user_index');
    }
    /**
    * @Route("/user/debloqueclient/{email}", name="deblocage", methods={"GET", "POST"})
      *@IsGranted("ROLE_ADMIN")
     */
    public function debloquer(String $email): Response
    {
        $entityManager = $this->getDoctrine()->getManager();
        $users = $this->getDoctrine()->getRepository(User::class)->findBy(array('email' => $email));
            $user = $users[0];
            $user->setRole("client"); 
            $user->setRoles(["ROLE_USER"]);   
            $entityManager->flush();
            return $this->redirectToRoute('app_user_index');
    }
    
}
