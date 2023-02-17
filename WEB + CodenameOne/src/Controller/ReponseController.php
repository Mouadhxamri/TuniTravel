<?php
namespace App\Controller;

use App\Entity\Reclamation;
use App\Entity\Reponse;
use App\Entity\User;
use App\Form\ReponseType;
use App\Repository\ReponseRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;

/**
 * @Route("/reponse")
 */
class ReponseController extends AbstractController
{
    /**
     * @Route("/", name="app_reponse_index", methods={"GET"})
     * @IsGranted("ROLE_ADMIN")
     */
    public function index(ReponseRepository $reponseRepository): Response
    {
        return $this->render('reponse/index.html.twig', [
            'reponses' => $reponseRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new/{id}", name="app_reponse_new", methods={"GET","POST"})
     * @IsGranted("ROLE_ADMIN")
     */
    public function new(Request $request,EntityManagerInterface $entityManager, Reclamation $reclamation, \Swift_Mailer $mailer): Response
       // public function new(Request $request, EntityManagerInterface $entityManager,int $id, \Swift_Mailer $mailer): Response
    {
        $reponse = new Reponse();
        $form = $this->createForm(ReponseType::class, $reponse);
        $form->handleRequest($request);


        if ($form->isSubmitted() && $form->isValid()) {
          /*  $reclamation = $entityManager
                ->getRepository(Reclamation::class)
                ->find($id);*/

            $reclamation->setEtat('Traité');
            $reponse->setReclamation($reclamation);

            $ResponseFile = $form->get('file')->getData();
            if ($ResponseFile) {
                $newFilename = md5(uniqid()).'-'.$ResponseFile->guessExtension();
                $ResponseFile->move(
                    $this->getParameter('files_directory'),
                    $newFilename
                );
                $reponse->setFile($newFilename) ;
            }

            $entityManager->persist($reponse);
            $entityManager->flush();
            //envoyer mail
            //$username=$this->getUser()->getUsername();
            $body=$reponse->getBody();
            $file=$reponse->getFile();
            $userId = $reclamation->getIdUser();
            $user = $entityManager
                ->getRepository(User::class)
                ->find($userId);
            $userE=$user->getEmail();
            $message = (new \Swift_Message($reponse->getObjet()))
                ->setFrom('hadilsfar8@gmail.com')
                ->setTo($userE)
                ->attach(fopen('/public/uploads/files/'.$file ,'r'))
                ->setBody(
                    $this->renderView(
                    // templates/emails/registration.html.twig
                        'emails/registration.html.twig',
                        [
                            //'username' => $username,
                            'body'=>$body,

                        ]
                    ),
                    'text/html'
                )

            ;
            $mailer->send($message);

            return $this->redirectToRoute('app_reclamation_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reponse/new.html.twig', [
            'reponse' => $reponse,
            'form' => $form->createView(),
        ]);
    }



    /**
     * @Route("/edit/{id}", name="app_reponse_edit", methods={"GET", "POST"})
     * @IsGranted("ROLE_ADMIN")
     */
    public function edit(Request $request, Reponse $reponse, EntityManagerInterface $entityManager, \Swift_Mailer $mailer): Response
    {
        $form = $this->createForm(ReponseType::class, $reponse);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $ResponseFile = $form->get('file')->getData();
            if ($ResponseFile) {
                $newFilename = md5(uniqid()).'-'.$ResponseFile->guessExtension();
                $ResponseFile->move(
                    $this->getParameter('files_directory'),
                    $newFilename
                );
                $reponse->setFile($newFilename) ;
            }
            $entityManager->persist($reponse);
            $entityManager->flush();

            //envoyer mail
            //$username=$this->getUser()->getUsername();
            $body=$reponse->getBody();
            $file=$reponse->getFile();

            $message = (new \Swift_Message($reponse->getObjet()))
                ->setFrom('tunitravel.agency@gmail.com')
                ->setTo('hedil.sfar@esprit.tn')
                // ->attach(fopen('/public/uploads/files/'.$file ,'r'))
                ->setBody(
                    $this->renderView(
                    // templates/emails/registration.html.twig
                        'emails/update.html.twig',
                        [
                            //'username' => $username,
                            'body'=>$body,

                        ]
                    ),
                    'text/html'
                )

            ;
            $mailer->send($message);


            return $this->redirectToRoute('app_reclamation_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reponse/edit.html.twig', [
            'reponse' => $reponse,
            'form' => $form->createView(),
        ]);
    }
    /**
     * @Route("/show/{id}", name="app_reponse_show", methods={"GET"})
     * @IsGranted("ROLE_ADMIN")
     */
    public function show(Reponse $reponse): Response
    {
        return $this->render('reponse/show.html.twig', [
            'reponse' => $reponse,
        ]);
    }



    /**
     * @Route("/{id}", name="app_reponse_delete", methods={"POST"})
     */
    public function delete(Request $request, Reponse $reponse, ReponseRepository $reponseRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$reponse->getId(), $request->request->get('_token'))) {
            $reponseRepository->remove($reponse);
        }

        return $this->redirectToRoute('app_reponse_index', [], Response::HTTP_SEE_OTHER);
    }

}
